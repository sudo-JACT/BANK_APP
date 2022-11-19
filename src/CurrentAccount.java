public class CurrentAccount {

    private final Utente[] intestatari = new Utente[1000];
    private int N_intestatari=0;
    private float saldo=0;
    private final String codice;
    private final String numero_conto;
    private boolean chiuso=false;

    public CurrentAccount(Utente u, float s, String nc, String cod){

        this.intestatari[0] = u;
        this.N_intestatari++;
        this.saldo = s;
        this.numero_conto = nc;
        this.codice = cod;

    }

    public CurrentAccount(String nc, String cod){

        this.numero_conto = nc;
        this.codice = cod;
    }


    public boolean set_saldo(String nc, String cod, float s){

        if(this.chiuso == false && this.numero_conto.equals(nc) && this.codice.equals(cod)){

            this.saldo = s;

            return true;

        }else{

            return false;

        }
    }

    public float prelev(String nc, String cod, float s){

        if(this.chiuso == false && this.numero_conto.equals(nc) && this.codice.equals(cod) && this.saldo > 0){

            this.saldo -= s;

            return s;

        }else{

            return -404;
        }

    }

    public float vers(String nc, String cod, float s){

        if(this.chiuso == false && this.numero_conto.equals(nc) && this.codice.equals(cod) && this.saldo > 0){

            this.saldo += s;

            return this.saldo;

        }else{

            return -1;
        }


    }

    public boolean bonifico(CurrentAccount c, String nc1, String nc2, String cod, float s){

        if(this.chiuso == false && this.numero_conto.equals(nc2) && this.codice.equals(cod) && this.saldo > 0){

            if(c.get_nc().equals(nc1)){

                this.saldo -= s;

                if(c.vers(nc1, c.get_cod(), s) != -1){

                    return true;

                }else{

                    return false;

                }


            }else{

                return false;

            }


        }else{

            return false;
        }

    }


    public boolean mod_conto(String nc, String cod){

        if(this.numero_conto.equals(nc) && this.codice.equals(cod)){

            this.chiuso = !this.chiuso;

            return true;

        }else{

            return false;
        }
    }

    public boolean add_user(String nc, String cod, Utente user, int in){

        if(this.chiuso == false && this.numero_conto.equals(nc) && this.codice.equals(cod)){

            this.intestatari[in] = user;
            this.N_intestatari++;

            return true;

        }else{

            return false;
        }

    }

    public boolean rem_user(String nc, String cod, int in){

        if(this.chiuso == false && this.numero_conto.equals(nc) && this.codice.equals(cod)){

            this.intestatari[in] = null;
            this.N_intestatari--;

            return true;

        }else{

            return false;
        }

    }



    public String get_nc(){

        return this.numero_conto;
    }

    public String get_cod(){

        return this.codice;
    }

    public float get_saldo(){

        return this.saldo;
    }

    public boolean get_stato(){

        return this.chiuso;
    }

    public Utente get_user(int in){

        return this.intestatari[in];
    }

    public int get_n_intestatari(){

        return N_intestatari;
    }

    public String toString(int a){

        return this.intestatari[a].toString() + " >> " + this.saldo;
    }
}
