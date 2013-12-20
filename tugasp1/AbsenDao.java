import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AbsenDao {
    private List<Absen> database = new ArrayList<Absen>();
    
    public AbsenDao(String namafile, PegawaiDao pegawaiDao){
        try {

            FileReader absenCsv = new FileReader(namafile);
            
            BufferedReader reader = new BufferedReader(absenCsv);
 
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      
            String data = reader.readLine(); 
            while((data = reader.readLine()) != null){
                String[] baris = data.split(","); 
                
                String nomer = baris[0]; 
                String tanggal = baris[1]; 
                String jamDatang = baris[2]; 
                String jamPulang = baris[3];                 
                
       
                Absen a = new Absen();
                Pegawai p = pegawaiDao.cariByNomer(nomer);
                a.setPegawai(p);
                
                a.setDatang(parser.parse(tanggal + " " +jamDatang));
                a.setPulang(parser.parse(tanggal + " " +jamPulang));
                
                database.add(a);
            }
            
            reader.close();
        } catch (Exception err) {
            System.out.println("Gagal loading data absen");
            err.printStackTrace();
        }
    }
    
    public List<Absen> semuaData(){
        return database;
    }
}
