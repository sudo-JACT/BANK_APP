/**
 * @version 1.2.0
 * @author 1MD0NK3Y
 * 
 */


public class Utente {

    private static int current_h = 2022;

    private final String nome;
    private final String cognome;
    private final String codice_univoco;
    private int eta;
    private char sesso;
    private final int anno_di_nascita;
    private CurrentAccount[] conti_correnti = new CurrentAccount[1000];
    private int N_currentacc=0;

    public Utente(String na, String co, int e, char s, String cu){

        this.nome = na;
        this.cognome = co;
        this.eta = e;
        this.sesso = s;
        this.anno_di_nascita = current_h - e;
        this.codice_univoco = cu;
        
    }

    public int inc_eta(){

        this.eta++;       
        return this.eta;
    }

    public boolean add_current(CurrentAccount c, String nc, String cod){

        if(c.get_nc().equals(nc) && c.get_cod().equals(cod)){

            this.conti_correnti[this.N_currentacc] = c;
            this.N_currentacc++;
            
            return true;

        }else{

            return false;
        }

    }

    public boolean rem_current(String nc, String cod, int in){

        if(this.conti_correnti[in].get_nc().equals(nc) && this.conti_correnti[in].get_cod().equals(cod)){

            this.conti_correnti[in] = null;
            this.N_currentacc--;

            return true;

        }else{

            return false;
        }

    }
    
    public String get_name(){

        return this.nome;
    }

    public String get_cognome(){

        return this.cognome;
    }

    public int get_eta(){

        return this.eta;
    }

    public char get_sesso(){

        return this.sesso;
    }

    public int get_nascita(){

        return this.anno_di_nascita;
    }

    public CurrentAccount get_account(int in){

        return this.conti_correnti[in];
    }

    public String get_account_nc(int in){

        return this.conti_correnti[in].get_nc();
    }

    public int get_conti(){

        return this.N_currentacc;
    }

    public String get_cu(){

        return this.codice_univoco;
    }

    public void set_conto(int i, CurrentAccount conto){

        this.conti_correnti[i] = conto;

    }

    public String toString(){

        return "Nome: " + this.nome + "\nCognome: " + this.cognome + "\nSesso: " + this.sesso + "\nEtà: " + this.eta + "\nCodice univoco: " + this.codice_univoco;
    }
}
