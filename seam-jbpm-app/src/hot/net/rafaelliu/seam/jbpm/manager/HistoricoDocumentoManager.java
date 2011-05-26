package net.rafaelliu.seam.jbpm.manager;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.rafaelliu.seam.jbpm.domain.Documento;
import net.rafaelliu.seam.jbpm.process.AcmeProcess;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.international.StatusMessages;

@Name("historicoDocumentoManager")
//@AutoCreate
//@Scope(ScopeType.CONVERSATION)
public class HistoricoDocumentoManager {

	@RequestParameter
	private Integer docid;
	
	@In
	private AcmeProcess acmeProcess;
	
	public String historico() {
		return "listarHistorico";
	}
	
	public List<Documento> getDocumentos() {
		return acmeProcess.listarDocumentos();
	}
	
	public String download() {
		try {
			Documento documento = docid == null ? acmeProcess.getDocumentoAtual() : acmeProcess.getDocumento(docid);
			HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			resp.setContentLength(documento.getTamanho());
			resp.setContentType(documento.getMime());
			resp.addHeader("Content-disposition", "attachment; filename=\"" + documento.getNome() +"\"");
			ServletOutputStream os = resp.getOutputStream();
			os.write(documento.getBinario());
			os.flush();
			os.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
