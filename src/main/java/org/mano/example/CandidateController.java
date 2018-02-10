package org.mano.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("candidateController")
@SessionScoped
public class CandidateController implements Serializable {

	static final long serialVersionUID = 1L;
	
	private Candidate candidate = new Candidate();
	private String filter = "";

	@EJB
	private CandidateFacade candidateEJB;
	private List<Candidate> list = new ArrayList<>();

	public CandidateController() {
	}

	@PostConstruct
	private void init() {
		find();
	}

	public void create() {
		candidateEJB.create(candidate);
		find();
	}

	public void find() {
		list = candidateEJB.find(filter);
	}

	public void delete() {
		candidateEJB.delete(candidate);
		find();
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public CandidateFacade getCandidateEJB() {
		return candidateEJB;
	}

	public void setCandidateEJB(CandidateFacade candidateEJB) {
		this.candidateEJB = candidateEJB;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<Candidate> getList() {
		return list;
	}

	public void setList(List<Candidate> list) {
		this.list = list;
	}
}
