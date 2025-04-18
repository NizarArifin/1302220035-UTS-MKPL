package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	 private static final double TAX_RATE = 0.05;
	 private static final int BASE_TAX_FREE_INCOME = 54000000;
	 private static final int SPOUSE_TAX_FREE_INCOME = 4500000;
	 private static final int CHILD_TAX_FREE_INCOME = 1500000;
	 private static final int MAX_CHILDREN_FOR_TAX_DEDUCTION = 3;
	
	
	 public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
    
		int tax = 0;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > MAX_CHILDREN_FOR_TAX_DEDUCTION) {
			numberOfChildren = MAX_CHILDREN_FOR_TAX_DEDUCTION;
		}
		
		if (isMarried) {
			tax = (int) Math.round(TAX_RATE * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (BASE_TAX_FREE_INCOME + SPOUSE_TAX_FREE_INCOME + (numberOfChildren * CHILD_TAX_FREE_INCOME))));
		} else {
			tax = (int) Math.round(TAX_RATE * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - BASE_TAX_FREE_INCOME));
		}
		
		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}
	}
	
}
