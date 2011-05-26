package net.rafaelliu.seam.jbpm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("documento")
@AutoCreate
//@Scope(ScopeType.CONVERSATION)
public class Documento implements Serializable {

	@Id
	private Integer id;
	
	private Date data;
	private String usuario;
	
	private String nome;
    private String mime;
    private int tamanho;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public byte[] getBinario() {
		return binario;
	}
	public void setBinario(byte[] binario) {
		this.binario = binario;
	}
	private byte[] binario;
	
}
