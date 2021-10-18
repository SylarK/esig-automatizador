package br.com.amado.carga.model;

public enum VAGAS {

	NOVO_ENSINO_MEDIO_1S_MATUTINO("NOVO ENSINO MÉDIO - 1ª Série - MATUTINO"),
	ENSINO_FUNDAMENTAL_ANOS_FINAIS_9ANO_MATUTINO("ENSINO FUNDAMENTAL - ANOS FINAIS - 9º ANO REGULAR - MATUTINO");
	
	public final String label;

    private VAGAS(String label) {
        this.label = label;
    }
	
}
