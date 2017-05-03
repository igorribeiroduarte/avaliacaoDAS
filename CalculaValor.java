
public class CalculaValor {
	final Acesso _acesso;
	
	float valorTotal = 0;
	
	private int quantidadeMinutosQuandoAsHorasSaoIguais;
	private int quantidadeHorasQuandoOsMinutosSaoIguais;
	private int quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida;
	private int quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada;
	private int quantidadeHorasQuandoOsMinutosSaoDiferentes;
	private final int quantidadeHorasQuandoAsHorasSaoIguais = 0;
	private final int quantidadeMinutosQuandoOsMinutosSaoIguais = 0;
	
	public CalculaValor(Acesso _acesso) {
		this._acesso = _acesso;
	}
	
	public float calculaValorTotal(final int quantidadeHoras, final int quantidadeMinutos){
		if(quantidadeHoras >= 9)
			return _acesso.VALOR_DIARIA;
		else
			return (float) (quantidadeHoras * _acesso.VALOR_HORA + 
				Math.ceil(quantidadeMinutos / 15.0) * _acesso.VALOR_FRACAO);
	}
	
	public float calcular() {
		if (_acesso.horaSaida == _acesso.horaEntrada){
			quantidadeMinutosQuandoAsHorasSaoIguais = _acesso.minutosSaida - _acesso.minutosEntrada;
			
			valorTotal += calculaValorTotal(quantidadeHorasQuandoAsHorasSaoIguais, quantidadeMinutosQuandoAsHorasSaoIguais);
		}else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosEntrada == _acesso.minutosSaida){
			quantidadeHorasQuandoOsMinutosSaoIguais = _acesso.horaSaida - _acesso.horaEntrada;
			
			valorTotal += calculaValorTotal(quantidadeHorasQuandoOsMinutosSaoIguais, quantidadeMinutosQuandoOsMinutosSaoIguais);
		}
		else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosEntrada > _acesso.minutosSaida){
			quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida = _acesso.minutosSaida - _acesso.minutosEntrada;
			
			valorTotal += calculaValorTotal(quantidadeHorasQuandoOsMinutosSaoIguais, quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida);
		}else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosSaida < _acesso.minutosEntrada){
			quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada = _acesso.minutosSaida + (60 - _acesso.minutosEntrada);
			quantidadeHorasQuandoOsMinutosSaoDiferentes = _acesso.horaSaida - _acesso.horaEntrada - 1;
			
			valorTotal += calculaValorTotal(quantidadeHorasQuandoOsMinutosSaoDiferentes, quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada);
		}
		
		return valorTotal;
	}
	
	
}
