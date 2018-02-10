package org.mano.example;

public class Candidate {
	private int id;
	private String skillSet;
	private String name;
	private String email;
	private String phone;
	private String gender;
	private String lastDegree;
	private String lastDesig;
	private float expInYearMonth;

	public Candidate() {
	}

	public Candidate(int id, String skillSet, String name, String email, String phone, String gender, String lastDegree,
			String lastDesig, float expInYearMonth) {
		this.id = id;
		this.skillSet = skillSet;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.lastDegree = lastDegree;
		this.lastDesig = lastDesig;
		this.expInYearMonth = expInYearMonth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastDegree() {
		return lastDegree;
	}

	public void setLastDegree(String lastDegree) {
		this.lastDegree = lastDegree;
	}

	public String getLastDesig() {
		return lastDesig;
	}

	public void setLastDesig(String lastDesig) {
		this.lastDesig = lastDesig;
	}

	public float getExpInYearMonth() {
		return expInYearMonth;
	}

	public void setExpInYearMonth(float expInYearMonth) {
		this.expInYearMonth = expInYearMonth;
	}
}