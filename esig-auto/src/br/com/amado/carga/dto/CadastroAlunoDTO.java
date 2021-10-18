package br.com.amado.carga.dto;


public class CadastroAlunoDTO {

	
	private String nomeEstudante;
	private String sexoEstudante;
	private String cpfEstudante;
	private String dataNascimento = "11112000";
	private String nomeMae = "Maria de Souza Ferreira";
	private String RG = "5999948";
	private String UF = "BA";
	private String OrgaoExpedicao = "SSP";
	private String dataExpedicao = "11/11/2010";
	
	@Override
	public String toString() {
		return "\nCadastro do Aluno\n[nomeEstudante=" + nomeEstudante + ", \nsexoEstudante=" + sexoEstudante
				+ ", \ncpfEstudante=" + cpfEstudante + ", \ndataNascimento=" + dataNascimento + ", \nnomeMae=" + nomeMae
				+ ", \nRG=" + RG + ", \nUF=" + UF + ", \nOrgaoExpedicao=" + OrgaoExpedicao + ", \ndataExpedicao="
				+ dataExpedicao + "]\n";
	}
	
	public String getNomeEstudante() {
		return nomeEstudante;
	}
	public void setNomeEstudante(String nomeEstudante) {
		this.nomeEstudante = nomeEstudante;
	}
	public String getSexoEstudante() {
		return sexoEstudante;
	}
	public void setSexoEstudante(String sexoEstudante) {
		this.sexoEstudante = sexoEstudante;
	}
	public String getCpfEstudante() {
		return cpfEstudante;
	}
	public void setCpfEstudante(String cpfEstudante) {
		this.cpfEstudante = cpfEstudante;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getOrgaoExpedicao() {
		return OrgaoExpedicao;
	}
	public void setOrgaoExpedicao(String orgaoExpedicao) {
		OrgaoExpedicao = orgaoExpedicao;
	}
	public String getDataExpedicao() {
		return dataExpedicao;
	}
	public void setDataExpedicao(String dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}	
}
