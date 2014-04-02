package utilisateurs.gestionnaires;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utilisateurs.modeles.Adresse;
import utilisateurs.modeles.Telephone;
import utilisateurs.modeles.Utilisateur;

/**
 * Complétez l'adresse en ajoutant un numéro de rue, complétez l'utilisateur en
 * ajoutant une liste de numéros de téléphones sous forme de relations. On veut
 * évidemment afficher le No de rue et la liste des numéros de téléphones dans
 * le tableau figurant dans la page JSP. Vous ajoutez à chaque utilisateur au
 * moins deux numéros de téléphones en précisant dans la classe Telephone.java
 * une propriété indiquant si c'est un mobile un fixe, etc. Si vous êtres
 * arrivés là, essayez de rajouter un formulaire pour ajouter un numéro de
 * téléphone à un utilisateur existant, ou bien pour en enlever un.
 *
 * @author Nicolas
 */
@Stateless
public class GestionnaireUtilisateurs {

    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext
    private EntityManager em;

    public void creerUtilisateursDeTest() {
        // On cree des adresses et on les insère dans la base  
        Adresse biot = new Adresse("Biot", "06410");
        em.persist(biot);
        Adresse valbonne = new Adresse("Valbonne", "06560");
        em.persist(valbonne);
        Adresse nice = new Adresse("Nice", "06000");
        em.persist(nice);
        
        Telephone tel1 = new Telephone("0102030405");
        em.persist(tel1);
        Telephone tel2 = new Telephone("0607080910");
        em.persist(tel2);
        Telephone tel3 = new Telephone("1112131415");
        em.persist(tel3);
        
        Set<Telephone> t1 = new HashSet<Telephone>();
        Set<Telephone> t2 = new HashSet<Telephone>();
        Set<Telephone> t3 = new HashSet<Telephone>();
        
        t1.add(tel1); t1.add(tel2);
        t2.add(tel1); t2.add(tel3);
        t3.add(tel2); t3.add(tel3);
        
        
        // Note : après un persist, les objets sont connectés  
        // John et Paul habitent à Biot  
        Utilisateur john = creeUtilisateur("John", "Lennon", "jlennon", "pass", biot, t1);
        Utilisateur paul = creeUtilisateur("Paul", "Mac Cartney", "pmc", "pass", biot, t2);
        Utilisateur ringo = creeUtilisateur("Ringo", "Starr", "rstarr", "pass", nice, t1);
        Utilisateur georges = creeUtilisateur("Georges", "Harisson", "georgesH", "pass", valbonne, t3);
    }

    public Utilisateur creeUtilisateur(String prenom, String nom, String login, String pass, Adresse a, Set<Telephone> t) {
        Utilisateur u = new Utilisateur(nom, prenom, login);
        // On met à jour la relation, elle est déjà en base  
        u.setAdresse(a);
        u.setTelephones(t);

        // a est déjà en base et connectée, donc la ligne suivante modifie les   
        // données pour relier l'adresse à l'utilisateur  
        a.addUtilisateur(u);
        

        // On persiste l'utilisateur, la relation est déjà en base, cela va donc  
        // ajouter une ligne dans la table des utilisateur avec une clé étrangère  
        // correspondant à l'adresse  
        em.persist(u);

        return u;
    }

    /**
     * Cherche les utilisateurs pas leur login
     *
     * @param login login de l'utilisateur
     * @return la liste des utilisateurs ayant le même login
     */
    public List<Utilisateur> chercherParLogin(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login = '" + login + "'");
        return q.getResultList();
    }

    public void modifierUtilisateur(String prenom, String nom, String login) {
        Query q = em.createQuery("update Utilisateur u "
                + "set u.firstname = '" + prenom + "' , " + "u.lastname = '" + nom + "' "
                + "where u.login = '" + login + "'");
        int numUpdates = q.executeUpdate();
    }

    /**
     * Supprimer un utilisateur en le selectionnant par son login
     *
     * @param login Le login de l'utilisateur à supprimer
     */
    public void supprimerUtilisateur(String login) {

        Utilisateur u = this.chercherParLogin(login).get(0); // on prend l'utilisateur qu'on recherche par le login
        em.remove(u); // on le supprime  

    }

    public Collection<Utilisateur> getAllUsers(int firstRow, int maxRow) {
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");
        q.setMaxResults(10);
        q.setFirstResult(firstRow);
        return q.getResultList();
    }

    public boolean userExists(String login, String pass) {
        Query q = em.createQuery("SELECT u FROM Utilisateur u");
        Collection<Utilisateur> users = q.getResultList();
        for (Utilisateur u : users) {
            if (u.getLogin().equals(login)) {
                if (u.getPass().equals(pass)) {
                    return true;
                }
            }
        }
        if (login.equals("admin") && pass.equals("admin")) {
            return true;
        }
        return false;
    }

    public Collection<Utilisateur> getAllUsers() {
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList();
    }

    public Collection<Utilisateur> getUsersParVille(int idVille) {
        Adresse a = em.find(Adresse.class, idVille);

        // a est connecté, le get va déclencher un select  
        return a.getUtilisateurs();
    }

    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")
    public void businessMethod() {
    }

}
