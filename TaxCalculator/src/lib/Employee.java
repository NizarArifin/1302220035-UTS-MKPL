package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private JoinDate joinDate;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.idNumber = idNumber;
    this.address = address;
    this.joinDate = new JoinDate(yearJoined, monthJoined, dayJoined);
    this.isForeigner = isForeigner;
    this.gender = gender;
    
    childNames = new LinkedList<String>();
    childIdNumbers = new LinkedList<String>();
}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	 private static final int GRADE_1_SALARY = 3000000;
	 private static final int GRADE_2_SALARY = 5000000;
	 private static final int GRADE_3_SALARY = 7000000;
	 private static final double FOREIGNER_RATE = 1.5;
	
	 public void setMonthlySalary(int grade) {    
		int baseSalary = 0;
		
		if (grade == 1) {
			baseSalary = GRADE_1_SALARY;
		} else if (grade == 2) {
			baseSalary = GRADE_2_SALARY;
		} else if (grade == 3) {
			baseSalary = GRADE_3_SALARY;
		}
		
		monthlySalary = baseSalary;
		
		if (isForeigner) {
			monthlySalary = (int) (baseSalary * FOREIGNER_RATE);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = spouseIdNumber;// Ganti parameter menjadi spouseIdNumber
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		// Menggunakan method dari JoinDate untuk menghitung bulan bekerja
		monthWorkingInYear = joinDate.getMonthsWorkedInCurrentYear();
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
