package lib;

import java.time.LocalDate;

// menyimpan tanggal join karyawan
public class JoinDate {
    private int year;
    private int month;
    private int day;
    
    public JoinDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getDay() {
        return day;
    }
    
    public int getMonthsWorkedInCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        
        if (currentDate.getYear() == year) {
            return currentDate.getMonthValue() - month;
        } else {
            return 12;
        }
    }
}