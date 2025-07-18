import java.util.HashMap;

public class IDandPassw {

    HashMap<String,String> logininfo = new HashMap<>();
    HashMap<String,String> userRoles = new HashMap<>();

    IDandPassw(){
        logininfo.put("s","s");
        logininfo.put("t","t");

        logininfo.put("Bălan_Amalia","1");
        logininfo.put("Barbu_Irina","2");
        logininfo.put("Cristea_Ștefan","3");
        logininfo.put("Diaconu_Roxana","4");
        logininfo.put("Dumitrescu_Paul","5");
        logininfo.put("Dumitru_Eric","6");
        logininfo.put("Georgescu_Vlad","7");
        logininfo.put("Ionescu_Maria","8");
        logininfo.put("Lungu_Adriana","9");
        logininfo.put("Matei_Denisa","10");
        logininfo.put("Petrescu_Alexandru","11");
        logininfo.put("Popescu_Andrei","12");
        logininfo.put("Tudor_Mihai","13");
        logininfo.put("Voicu_Cristian","14");
        logininfo.put("Vasile_Marius","15");
        logininfo.put("Bădescu_Dan","a");
        logininfo.put("Gherasim_Monica","b");
        logininfo.put("Preda_Mihai","c");
        logininfo.put("Stoica_Sorina","d");
        logininfo.put("Vornicu_Cristina","e");

        userRoles.put("s", "Student");
        userRoles.put("t", "Profesor");

        userRoles.put("Bălan_Amalia", "Student");
        userRoles.put("Barbu_Irina", "Student");
        userRoles.put("Cristea_Ștefan", "Student");
        userRoles.put("Diaconu_Roxana", "Student");
        userRoles.put("Dumitrescu_Paul", "Student");
        userRoles.put("Dumitru_Eric", "Student");
        userRoles.put("Georgescu_Vlad", "Student");
        userRoles.put("Ionescu_Maria", "Student");
        userRoles.put("Lungu_Adriana", "Student");
        userRoles.put("Matei_Denisa", "Student");
        userRoles.put("Petrescu_Alexandru", "Student");
        userRoles.put("Popescu_Andrei", "Student");
        userRoles.put("Tudor_Mihai", "Student");
        userRoles.put("Voicu_Cristian", "Student");
        userRoles.put("Vasile_Marius", "Student");
        userRoles.put("Bădescu_Dan", "Profesor");
        userRoles.put("Gherasim_Monica", "Profesor");
        userRoles.put("Preda_Mihai", "Profesor");
        userRoles.put("Stoica_Sorina", "Profesor");
        userRoles.put("Vornicu_Cristina", "Profesor");
    }

    protected HashMap<String, String> getLoginInfo() {
        return logininfo;
    }

    protected HashMap<String, String> getUserRoles() {
        return userRoles;
    }
}
