package csc1011;

public class Crime implements
java.io.Serializable {

	public Crime(String name, String commitedBy,
			CrimeSeverityLevel crimeSeverity,
			CrimeLocationsAvailable crimeLocation) {
		super();
		this.name = name;
		CommitedBy = commitedBy;
		CrimeSeverity = crimeSeverity;
		CrimeLocation = crimeLocation;
	}

	String name;
	String CommitedBy;
	CrimeSeverityLevel CrimeSeverity;
	CrimeLocationsAvailable CrimeLocation;
	int CrimeExpire = 20;
	
	
	public enum CrimeSeverityLevel {
		Low, Medium, Severe
	}
	
	public enum CrimeLocationsAvailable{
		Downtown, Central_Bank, Suburbs, Docks
	}
	
}
