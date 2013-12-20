import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class PegawaiDao {
    private List<Pegawai> database = new ArrayList<Pegawai>();

    public PegawaiDao(String namafile){
        try {
    
            FileReader tarifCsv = new FileReader(namafile);
            
        
            BufferedReader reader = new BufferedReader(tarifCsv);
            
         
            String data = reader.readLine(); 
            while((data = reader.readLine()) != null){
                String[] baris = data.split(","); 
                
             
                Pegawai p = new Pegawai();
                p.setNomer(baris[0]);
                p.setNama(baris[1]); 
                p.setGaji(new BigDecimal(baris[2]));
                p.setDenda(new BigDecimal(baris[3]));
                p.setLembur(new BigDecimal(baris[4])); 
                p.setTransport(new BigDecimal(baris[5]));
                
            
                database.add(p);
            }
            
         
            reader.close();
        } catch (Exception err){
            System.out.println("Gagal loading data pegawai");
            err.printStackTrace();
        }
    }
    
    public List<Pegawai> semuaPegawai(){
        return database;
    }
    
    public Pegawai cariByNomer(String nomer){
        for(Pegawai p : database){
            if(nomer.equals(p.getNomer())){
                return p;
            }
        }
        return null;
    }
}
