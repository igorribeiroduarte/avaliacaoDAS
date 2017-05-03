
public class Acesso {

	public static final float VALOR_FRACAO = (float) 2.0;
	public static final float VALOR_HORA = (float) 7.0;
	public static final float VALOR_DIARIA = (float) 30.0;
	
	public String placa;
	public int dia, mes, ano;
	public int horaEntrada, 
	           minutosEntrada;
	public int horaSaida, 
			   minutosSaida;
	


	public Acesso() {}

	
	public Acesso(int dia, int mes, int ano, int horaEntrada, int minutosEntrada) { 
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.horaEntrada = horaEntrada;
		this.minutosEntrada = minutosEntrada;
	}
	
	
	public float calcularValor() {
		float valorTotal = 0; 
		
		if (horaSaida == horaEntrada){
			final int quantidadeMinutosQuandoAsHorasSaoIguais = minutosSaida - minutosEntrada;
			
			valorTotal += Math.ceil(quantidadeMinutosQuandoAsHorasSaoIguais / 15.0) * VALOR_FRACAO;
		}else if (horaSaida > horaEntrada && minutosEntrada == minutosSaida){
			final int quantidadeHorasQuandoOsMinutosSaoIguais = horaSaida - horaEntrada;
			
			if(quantidadeHorasQuandoOsMinutosSaoIguais >= 9)
				return VALOR_DIARIA;
			
			valorTotal += quantidadeHorasQuandoOsMinutosSaoIguais * VALOR_HORA;
		}
		else if (horaSaida > horaEntrada && minutosEntrada > minutosSaida){
			final int quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida = minutosSaida - minutosEntrada;
			
			valorTotal += Math.ceil(quantidadeMinutosQuandoOsMinutosEntradaSaoMaioresQueOsMinutosSaida / 15.0) * VALOR_FRACAO;
		}else if (horaSaida > horaEntrada && minutosSaida < minutosEntrada){
			final int quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada = minutosSaida + (60 - minutosEntrada);
			final int quantidadeHorasQuandoOsMinutosSaoDiferentes = horaSaida - horaEntrada - 1;
			
			if(quantidadeHorasQuandoOsMinutosSaoDiferentes >= 9)
				return VALOR_DIARIA;
			
			valorTotal += quantidadeHorasQuandoOsMinutosSaoDiferentes * VALOR_HORA;
			valorTotal += Math.ceil(quantidadeMinutosQuandoOsMinutosSaidaSaoMenoresQueOsMinutosEntrada / 15.0) * VALOR_FRACAO;
		}
		
		return valorTotal;
	}
	
	
	public void setHoraSaida(int horaSaida) {
		this.horaSaida = horaSaida;
	}


	public void setMinutosSaida(int minutosSaida) {
		this.minutosSaida = minutosSaida;
	}
	
	
	
}
