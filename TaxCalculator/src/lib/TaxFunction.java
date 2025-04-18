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
	 private static final int MAX_CHILDREN = 3;
	
	
	 public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
    // validasi input
    if (numberOfMonthWorking > 12) {
        System.err.println("More than 12 month working per year");
    }
    
    // batasi jumlah anak untuk perhitungan
    if (numberOfChildren > MAX_CHILDREN) {
        numberOfChildren = MAX_CHILDREN;
    }
    
    // hitung penghasilan tahunan
    int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
    
    // hitung penghasilan tidak kena pajak
    int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
    
    // hitung pajak
    int taxableIncome = annualIncome - deductible - nonTaxableIncome;
    int tax = (int) Math.round(TAX_RATE * taxableIncome);
    
    // pastikan pajak tidak negatif
    return tax < 0 ? 0 : tax;
}

// menghitung total penghasilan setahun
private static int calculateAnnualIncome(int monthlySalary, int otherIncome, int months) {
    return (monthlySalary + otherIncome) * months;
}

// menghitung PTKP (Penghasilan Tidak Kena Pajak)
private static int calculateNonTaxableIncome(boolean isMarried, int children) {
    int result = BASE_TAX_FREE_INCOME;
    
    if (isMarried) {
        result += SPOUSE_TAX_FREE_INCOME;
        result += (children * CHILD_TAX_FREE_INCOME);
    }
    
    return result;
}
	
}
