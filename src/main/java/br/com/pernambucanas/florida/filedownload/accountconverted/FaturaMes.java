package br.com.pernambucanas.florida.filedownload.accountconverted;

public enum FaturaMes {
	JAN(1), FEV(2), MAR(3), ABR(4), MAI(1), JUN(2), JUL(3), AGO(4), SET(1) ,OUT(2), NOV(3), DEZ(4);

	private final int valor;
	
	FaturaMes(int valorOpcao){
		valor = valorOpcao;
	}
	
	public int getValor(){
		return valor;
	}
}
