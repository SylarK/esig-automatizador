package br.com.amado.carga.model;

public enum COMPONENTES_ELETIVOS {

	ARTES_GRAFICAS("ARTES GRAFICAS"), 
	COMPONENTE_TESTE("COMPONENTE TESTE"),
	OFICINA_LEITURA("OFICINA DE LEITURA"),
	INFORMATICA("INFORMATICA"),
	AUDIOVISUAL("AUDIOVISUAL");
	
	public final String label;

    private COMPONENTES_ELETIVOS(String label) {
        this.label = label;
    }
	
}
