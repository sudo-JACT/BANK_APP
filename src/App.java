import java.util.Scanner;
import java.io.Console;

public class App {

    //* BANNER
    // Printa un ascii art 
    static void print_banner(){

        System.out.println("'########:::::'###::::'##::: ##:'##:::'##:");
        System.out.println(" ##.... ##:::'## ##::: ###:: ##: ##::'##::");
        System.out.println(" ##:::: ##::'##:. ##:: ####: ##: ##:'##:::");
        System.out.println(" ########::'##:::. ##: ## ## ##: #####::::");
        System.out.println(" ##.... ##: #########: ##. ####: ##. ##:::");
        System.out.println(" ##:::: ##: ##.... ##: ##:. ###: ##:. ##::");
        System.out.println(" ########:: ##:::: ##: ##::. ##: ##::. ##:");
        System.out.println("........:::..:::::..::..::::..::..::::..::");
        
    }

    // * ADD UTENTE
    static int add_utente(Utente[] u, int n_u, Scanner s, Console con, String[] seriali, int MAX_NS){

        int eta;
        String nome, cognome, cu, se;

        System.out.print("Nome: ");
        nome = s.next();
        System.out.print("Cognome: ");
        cognome = s.next();
        System.out.print("Età: ");
        eta = s.nextInt();
        System.out.print("Sesso: ");
        se = s.next();
        cu = serialize(seriali, MAX_NS, nome, cognome, se, eta);

        u[n_u] = new Utente(nome, cognome, eta, se.charAt(0), cu);
        System.out.println(u[n_u].toString());
        con.readLine();
        n_u++;

        return n_u;

    }


    // * ADD ACCOUNT
    static int add_account(Utente[] u, int n_u, CurrentAccount[] c0, int n_c, Scanner s, Console con){

        String nome, cognome, cu, nc, cod;
        float saldo;
        boolean F=false;
        int i, j;

        System.out.print("Il conto ha un utente?[y/n]\n>>");

        switch (s.next().charAt(0)) {

            case 'S':

            case 's':

            case 'Y':

            case 'y':

                System.out.print("Codice Univoco: ");
                cu = s.next();
                System.out.print("Numero conto: ");
                nc = s.next();
                System.out.print("Codice conto: ");
                cod = s.next();
                System.out.print("Saldo: ");
                saldo = s.nextFloat();
                F = false;
                            

                for(i=0; i < n_u && !F; i++){


                    if(u[i].get_cu().equals(cu)){


                        c0[n_c] = new CurrentAccount(u[i], saldo, nc, cod);
                        u[i].add_current(c0[n_c], nc, cod);
                        System.out.println(c0[n_c].toString(0));
                        con.readLine();
                        F = true;
                        n_c++;
                        break;
                                    

                    }

                }

                            
                if(!F){

                    System.out.println("Utente " + cu + " non trovato");
                    con.readLine();

                }
                            
                break;
                    
            default:

                System.out.print("Numero conto: ");
                nc = s.next();
                System.out.print("Codice conto: ");
                cod = s.next();

                c0[n_c] = new CurrentAccount(nc, cod);
                System.out.println(c0[n_c].get_nc());
                con.readLine();
                n_c++;
                    
                break;
        }

        return n_c;

    }

    // * RIMUOVI UTENTE
    static int rem_utente(Utente[] u, int n_u, Scanner s, Console con){

        int i;
        String cu;
        boolean F;

        F = false;
        System.out.print("Codice Univoco: ");
        cu = s.next();


        for(i=0; i < n_u && !F; i++){


            if(u[i].get_cu().equals(cu)){


                u[i] = null;
                System.out.println("Utente " + cu + " eliminato con successo");
                con.readLine();
                F = true;
                n_u--;
                
                                    

            }

        }

                            
        if(!F){

            System.out.println("Utente " + cu + " non trovato");
            con.readLine();

        }


        return n_u;
    }


    // * CHIUDI CONTO
    static int block_curr(Utente[] u, CurrentAccount[] c0, int n_c, Scanner s, Console con){

        int i;
        String nc;
        boolean F;

        F = false;
        System.out.print("Numero Conto: ");
        nc = s.next();


        for(i=0; i < n_c && !F; i++){


            if(c0[i].get_nc().equals(nc)){


                u[i] = null;
                System.out.println("Conto " + nc + " chiuso con successo");
                con.readLine();
                F = true;
                n_c--;
                break;
                                    

            }

        }

                            
        if(!F){

            System.out.println("Conto " + nc + " non trovato");
            con.readLine();

        }


        return n_c;
    }

    // * Users

    static void users(Utente[] u, int n_u, Console con){

        int i;

        if(n_u == 0){

            System.out.println("Ancora nessun Utente creato");
            con.readLine();

        }else{

            for(i=0; i < n_u; i++){

                System.out.println(i+1 + ")\n" + u[i].toString() + "\n#####################");
                        

            }
              

        }

        con.readLine();

    }

    // * Conti

    static void conti(CurrentAccount[] c0, int n_c, Console con){

        int i;

        if(n_c == 0){

            System.out.println("Ancora nessun Conto creato");
            con.readLine();

                        

        }else{

            for(i=0; i < n_c; i++){

                if(c0[i].get_stato() == true){

                    System.out.println(i+1 + ")" + c0[i].get_nc() + "(" + c0[i].get_saldo() + ")>> Chiuso");

                }else{

                    System.out.println(i+1 + ")" + c0[i].get_nc() + "(" + c0[i].get_saldo() +")>> Aperto");

                }

                        

            }

        }
                    
        con.readLine();

    }

    // * LOGIN
    // ! BUG
    static void login(Utente[] u, CurrentAccount[] c, int n_u, Scanner s, Console con){

        int i, sce, j, k;
        float q;
        String cu, scelt, cod, cont_dest;
        boolean F, T=true, F2, F3;
        CurrentAccount acc, acc2;

        System.out.print("Codice Univoco: ");
        cu = s.next();
        F = false;
        F2 = false;

        for(i=0; i < n_u && !F; i++){

            if(u[i].get_cu().equals(cu)){

                F = true;

                while(T){

                    clear_screen();
                    System.out.println("Benvenuto/a " + u[i].get_name() + " " + u[i].get_cognome());


                    System.out.print("Opzioni:\n1)Preleva\n2)Versa\n3)Fai bonifico\n4)Esci\n>>");
                    sce = s.nextInt();

                    switch (sce) {

                        case 1:

                            for(j=0; j < u[i].get_conti(); j++){

                                acc = u[i].get_account(j);

                                System.out.println(j+1 + ")" + acc.get_nc() + " (" + acc.get_saldo() + ")");

                            }
                            System.out.print("Numero Conto >>");
                            scelt = s.next();

                            for(j=0; j < u[i].get_conti(); j++){

                                acc = u[i].get_account(j);

                                if(acc.get_nc().equals(scelt)){

                                    F2 = true;

                                    System.out.print("Codice >>");
                                    cod = s.next();
                                    System.out.print("Preleva >>");
                                    q = s.nextFloat();
                                    acc.prelev(scelt, cod, q);
                                    System.out.println("Saldo attuale " + acc.get_saldo());
                                    u[i].set_conto(i, acc);
                                    con.readLine();

                                }


                            }

                            if(!F2){

                                System.out.println("Conto " + scelt + " non trovato");
                                con.readLine();

                            }
                            
                            break;



                        case 2:


                            for(j=0; j < u[i].get_conti(); j++){

                                acc = u[i].get_account(j);

                                System.out.println(j+1 + ")" + acc.get_nc() + " (" + acc.get_saldo() + ")");

                            }
                            System.out.print("Numero Conto >>");
                            scelt = s.next();

                            for(j=0; j < u[i].get_conti(); j++){

                                acc = u[i].get_account(j);

                                if(acc.get_nc().equals(scelt)){

                                    F2 = true;

                                    System.out.print("Codice >>");
                                    cod = s.next();
                                    System.out.print("Versa >>");
                                    q = s.nextFloat();
                                    acc.vers(scelt, cod, q);
                                    System.out.println("Saldo attuale " + acc.get_saldo());
                                    u[i].set_conto(i, acc);
                                    con.readLine();

                                }


                            }

                            if(!F2){

                                System.out.println("Conto " + scelt + " non trovato");
                                con.readLine();

                            }

                            break;



                        case 3:

                            for(j=0; j < u[i].get_conti(); j++){

                                acc = u[i].get_account(j);

                                System.out.println(j+1 + ")" + acc.get_nc() + " (" + acc.get_saldo() + ")");

                            }
                            System.out.print("Numero Conto mittente>>");
                            scelt = s.next();

                            for(j=0; j < u[i].get_conti(); j++){

                                acc = u[i].get_account(j);

                                if(acc.get_nc().equals(scelt)){

                                    F2 = true;

                                    System.out.print("Codice >>");
                                    cod = s.next();
                                    System.out.print("Numero Conto destinatario>>");
                                    cont_dest = s.next();

                                    F3 = false;

                                    for(k=0; k < u[i].get_conti(); k++){

                                        if(c[k].get_nc().equals(cont_dest)){

                                            F3 = true;

                                            acc2 = c[k];

                                            System.out.print("Versa >>");
                                            q = s.nextFloat();

                                            acc.bonifico(acc2, cont_dest, scelt, cod, q);
                                            c[k] = acc2;

                                        }
                                        
                                    }

                                    if(!F3){

                                        System.out.println("Conto " + cont_dest + " non trovato");
                                        con.readLine();

                                    }else{

                                        System.out.println("Saldo attuale " + acc.get_saldo());
                                        u[i].set_conto(i, acc);
                                        con.readLine();

                                    }
                                }


                            }

                            if(!F2){

                                System.out.println("Conto " + scelt + " non trovato");
                                con.readLine();

                            }

                            
                            break;

                        case 4:
                            
                            T = false;

                            break;
                    
                        default:
                            System.out.println("Opzione non valida");
                            con.readLine();
                            break;
                    }

                }


            }

        }

        if(!F){

            System.out.println("Utente " + cu + " non trovato");
            con.readLine();

        }

    }

    static boolean add(String[] seriali, int MAX_NS, String S){

        int i;

        for(i=0; i < MAX_NS; i++){

            if(seriali[i].equals("")){

                seriali[i] = S;

                return true;

            }

        }

        return false;

    }

    static boolean find(String[] seriali, int MAX_NS, String S){

        int i;
        boolean F=false;

        for(i=0; i < MAX_NS; i++){

            if(S.equals(seriali[i])){

                F = true;

            }


        }

        return F;

    }

    static String serialize(String[] seriali, int MAX_NS, String nome, String cognome, String sesso, int eta){

        int i, j, lenn=nome.length(), lenc=cognome.length();
        char se = sesso.toLowerCase().charAt(0);
        String S;

        nome = nome.toLowerCase();
        cognome = cognome.toLowerCase();

        for(i=0; i < lenn; i++){

            for(j=0; j < lenc; j++){

                S = "" + nome.charAt(i) + cognome.charAt(j) + se + eta;

                if(find(seriali, MAX_NS, S) == false){

                    add(seriali, MAX_NS, S);

                    return S;

                }

            }
        
        }

        return null;        
    }

    // * CLEAR SCREEN
    static void clear_screen(){

        System.out.print("\033[H\033[2J"); 
        System.out.flush();

    }


//------------------------------------------------------------------------------------------------------



    // * MAIN
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Console con = System.console();
        int MAX_V = 2000;
        int c, n_u=0, n_c=0, eta, i, j;
        float saldo;
        String nome, cognome, nc, cod, cu;
        char se;
        boolean T=true, F = false, F2 = false;


        Utente[] Utenti = new Utente[MAX_V];
        CurrentAccount[] c0 = new CurrentAccount[MAX_V];
        String[] serials = new String[MAX_V];

        for(i=0; i < MAX_V; i++){

            serials[i] = "";

        }


        while(T){

            clear_screen();
            print_banner();
            System.out.print("Opzioni:\n1)Crea Utente (Utenti attuali: " + n_u + ")\n2)Crea Conto (Conti attuali: " + n_c + ")\n3)Rimuovi Utente\n4)Chiudi Conto\n5)Modifica Conto\n6)Mostra Utenti\n7)Mostra Conti\n8)Accedi\n9)Esci\n>>");

            c = s.nextInt();

            switch(c){
                case 1:

                    n_u = add_utente(Utenti, n_u, s, con, serials, MAX_V);
                    
                    break;


                case 2:

                    n_c = add_account(Utenti, n_u, c0, n_c, s, con);
                    
                    break;

                case 3:

                    n_u = rem_utente(Utenti, n_u, s, con);
                    
                    break;

                case 4:

                    n_c = block_curr(Utenti, c0, n_c, s, con);
                    
                    break;

                

                case 5:

                    System.out.print("Inserisci Numero Conto>> ");
                    nc = s.next();
                    System.out.print("Inserisci Codice Conto>> ");
                    cod = s.next();

                    System.out.print("1)Aggiungi intestatario\n2)Rimuovi intestatario\n>>");

                    switch (s.nextInt()) {

                        case 1:

                            F = false;
                            F2 = false;

                            for(i=0; i < n_c && !F; i++){

                                if(c0[i].get_nc().equals(nc)){

                                    F = true;

                                    System.out.print("Codice Univoco: ");
                                    cu = s.next();

                                    for(j=0; j < n_u && !F2; j++){

                                        if(Utenti[j].get_cu().equals(cu)){
                                            F2 = true;

                                            c0[i].add_user(nc, cod, Utenti[j], c0[i].get_n_intestatari());
                                            Utenti[j].add_current(c0[i], nc, cod);
                                            System.out.println("Utente " + cu + " aggiunto con successo");
                                            con.readLine();

                                        }

                                    }

                                    if(!F2){

                                        System.out.println("Utente " + cu + " non trovato");
                                        con.readLine();

                                    }

                                }

                            }

                            if(!F){

                                System.out.println("Conto " + nc + " non trovato");
                                con.readLine();

                            }
                            
                            break;
                    
                        default:

                            F = false;
                            F2 = false;

                            for(i=0; i < n_c && !F; i++){

                                if(c0[i].get_nc().equals(nc)){

                                    F = true;

                                    System.out.print("Codice Univoco: ");
                                    cu = s.next();

                                    for(j=0; j < n_u && !F2; j++){

                                        if(Utenti[j].get_cu().equals(cu)){
                                            F2 = true;

                                            c0[i].rem_user(nc, cod, c0[i].get_n_intestatari()-1);
                                            Utenti[j].rem_current(nc, cod, Utenti[j].get_conti()-1);
                                            System.out.println("Utente " + cu + " rimosso con successo");
                                            con.readLine();

                                        }

                                    }

                                    if(!F2){

                                        System.out.println("Utente " + cu + " non trovato");
                                        con.readLine();

                                    }

                                }

                            }

                            if(!F){

                                System.out.println("Conto " + nc + " non trovato");
                                con.readLine();

                            }

                            break;
                    }
                    
                    break;

                case 6:

                    users(Utenti, n_u, con);    

                    break;

                case 7:

                    conti(c0, n_c, con);    

                    break;


                case 8:

                    login(Utenti, c0, n_u, s, con);
                    
                    break;


                case 9:

                    T = false;
                    System.out.println("Uscendo...");
                    //con.readLine();
                    break;

                case 54:

                    System.out.println(serialize(serials, MAX_V, "Mario", "Rossi", "M", 32));
                    con.readLine();
                    
                    break;

                default:
                    System.out.println("Opzione non valida");
                    con.readLine();
                    break;
            }
        }

    }

}



/**
 * TODO: 
 *      Sistemare bug nel login, abbellire il codice
 */
