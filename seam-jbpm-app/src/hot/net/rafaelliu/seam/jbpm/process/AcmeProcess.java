package net.rafaelliu.seam.jbpm.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.rafaelliu.seam.jbpm.domain.Documento;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.ResumeProcess;
import org.jboss.seam.bpm.ProcessInstanceFinder;
import org.jboss.seam.security.Credentials;
import org.jbpm.graph.exe.ProcessInstance;


@Name("acmeProcess")
@AutoCreate
public class AcmeProcess {

	@In(scope = ScopeType.BUSINESS_PROCESS, required = false)
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	private String descricao;

	@In(scope = ScopeType.BUSINESS_PROCESS, required = false)
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	private String tipo;

	@In(scope = ScopeType.BUSINESS_PROCESS, required = false)
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	private String comentario;

	@In(scope = ScopeType.BUSINESS_PROCESS, required = false)
	@Out(scope = ScopeType.BUSINESS_PROCESS, required = false)
	private List<Documento> documentos;
	
	@In
	private Credentials credentials;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@CreateProcess(definition = "ComunicadoAcme")
	public void iniciarProcesso() {
		if (descricao == null) {
			throw new IllegalStateException(
					"Tentativa de iniciar o processo sem definir o tamanho da compra");
		}
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<ProcessInstance> getProcessos() {
		return new ProcessInstanceFinder() {{
			setProcessInstanceEnded(null);
		}}.getProcessInstanceList();
	}

	public void adicionarDocumento(Documento documento) {
		if (documentos == null) {
			documentos = new ArrayList<Documento>();
		}
		documento.setUsuario(credentials.getUsername());
		documento.setData(new Date());
		documentos.add(documento);
	}

	@ResumeProcess(definition="ComunicadoAcme", processIdParameter="pid")
	public List<Documento> listarDocumentos() {
		return documentos;
	}

	@ResumeProcess(definition="ComunicadoAcme", processIdParameter="pid")
	public Documento getDocumento(Integer index) {
		return documentos.get(index);
	}

	public Documento getDocumentoAtual() {
		return documentos.get(documentos.size()-1);
	}

}
