package br.com.amado.carga.model;

public enum ESCOLAS {

	ESCOLA_LACERDA("ESCOLA TREINAMENTO LACERDA"),
	ESCOLA_OLIVEIRA("ESCOLA TREINAMENTO OLIVEIRA"),
	ESCOLA_CABRAL("ESCOLA TREINAMENTO CABRAL"),
	ESCOLA_ILE_AIYE("ESCOLA TREINAMENTO ILE AIYE");
	
	public final String label;

    private ESCOLAS(String label) {
        this.label = label;
    }
	
}
