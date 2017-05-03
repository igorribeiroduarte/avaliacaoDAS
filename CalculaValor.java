
public class CalculaValor {
	final Acesso _acesso;
	
	float valorTotal = 0;
	
	private int quantidadeMinutosQuandoAsHorasSaoIguais;
	private int quantidadeHorasQuandoOsMinutosSaoIguais;
	private int quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida;
	private int quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada;
	private int quantidadeHorasQuandoOsMinutosSaoDiferentes;
	
	public CalculaValor(Acesso _acesso) {
		this._acesso = _acesso;
	}
	
	public float calcular() {
		if (_acesso.horaSaida == _acesso.horaEntrada){
			quantidadeMinutosQuandoAsHorasSaoIguais = _acesso.minutosSaida - _acesso.minutosEntrada;
			
			valorTotal += Math.ceil(quantidadeMinutosQuandoAsHorasSaoIguais / 15.0) * _acesso.VALOR_FRACAO;
		}else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosEntrada == _acesso.minutosSaida){
			quantidadeHorasQuandoOsMinutosSaoIguais = _acesso.horaSaida - _acesso.horaEntrada;
			
			if(quantidadeHorasQuandoOsMinutosSaoIguais >= 9)
				return _acesso.VALOR_DIARIA;
			
			valorTotal += quantidadeHorasQuandoOsMinutosSaoIguais * _acesso.VALOR_HORA;
		}
		else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosEntrada > _acesso.minutosSaida){
			quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida = _acesso.minutosSaida - _acesso.minutosEntrada;
			
			valorTotal += Math.ceil(quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida / 15.0) * _acesso.VALOR_FRACAO;
		}else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosSaida < _acesso.minutosEntrada){
			quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada = _acesso.minutosSaida + (60 - _acesso.minutosEntrada);
			quantidadeHorasQuandoOsMinutosSaoDiferentes = _acesso.horaSaida - _acesso.horaEntrada - 1;
			
			if(quantidadeHorasQuandoOsMinutosSaoDiferentes >= 9)
				return _acesso.VALOR_DIARIA;
			
			valorTotal += quantidadeHorasQuandoOsMinutosSaoDiferentes * _acesso.VALOR_HORA;
			valorTotal += Math.ceil(quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada / 15.0) * _acesso.VALOR_FRACAO;
		}
		
		return valorTotal;
	}
	
	
}
