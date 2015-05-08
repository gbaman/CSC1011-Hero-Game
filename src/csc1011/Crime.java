package csc1011;

public class Crime implements
java.io.Serializable {

	public Crime(String name,
			CrimeSeverityLevel crimeSeverity) {
		super();
		this.name = name;
		CrimeSeverity = crimeSeverity;
	}

	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CrimeSeverityLevel getCrimeSeverity() {
		return CrimeSeverity;
	}

	public void setCrimeSeverity(CrimeSeverityLevel crimeSeverity) {
		CrimeSeverity = crimeSeverity;
	}

	CrimeSeverityLevel CrimeSeverity;
	int CrimeExpire = 20;
	
	
	public int getCrimeExpire() {
		return CrimeExpire;
	}

	public void setCrimeExpire(int crimeExpire) {
		CrimeExpire = crimeExpire;
	}

	public enum CrimeSeverityLevel {
		easy, medium, difficult
	}
	
	
}
