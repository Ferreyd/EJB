package utilisateurs.gestionnaires;  
  
import java.util.ArrayList;
import java.util.Collection;  
import java.util.List;
import javax.ejb.Stateless;  
import javax.persistence.EntityManager;  
import javax.persistence.PersistenceContext;  
import javax.persistence.Query;  
import utilisateurs.modeles.Utilisateur;  
  
@Stateless  
public class GestionnaireUtilisateurs {  
    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext  
    private EntityManager em;  
  
    public void creerUtilisateursDeTest() {  
        creeUtilisateur("John", "Lennon", "jlennon", "pass");  
        creeUtilisateur("Paul", "Mac Cartney", "pmc", "pass");  
        creeUtilisateur("Ringo", "Starr", "rstarr", "pass");  
        creeUtilisateur("Georges", "Harisson", "georgesH", "pass");  
    }  
  
    public Utilisateur creeUtilisateur(String prenom, String nom, String login, String pass) {  
        Utilisateur u = new Utilisateur(prenom, nom, login, pass);  
        em.persist(u);  
        return u;  
    }  
    
     /**
     * Cherche les utilisateurs pas leur login
     * @param login login de l'utilisateur
     * @return la liste des utilisateurs ayant le même login
     */
    public List<Utilisateur> chercherParLogin(String login)
    {
        Query q = em.createQuery("select u from Utilisateur u where u.login = '" + login + "'");
        return q.getResultList();
    }
    

    public void modifierUtilisateur(String prenom, String nom, String login)
    {
        Query q = em.createQuery("update Utilisateur u " + 
                "set u.firstname = '" + prenom + "' , " + "u.lastname = '" + nom + "' " +
                "where u.login = '" + login + "'");
        int numUpdates = q.executeUpdate();
    }
    
     /**
      * Supprimer un utilisateur en le selectionnant par son login
      * @param login Le login de l'utilisateur à supprimer
      */
    public void supprimerUtilisateur(String login)
    {
        
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
   
  public boolean userExists(String login, String pass){
        Query q = em.createQuery("SELECT u FROM Utilisateur u");
        Collection <Utilisateur> users = q.getResultList();
        for (Utilisateur u : users){
            if(u.getLogin().equals(login) ){
                if(u.getPass().equals(pass)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public Collection<Utilisateur> getAllUsers() {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u"); 
        return q.getResultList();  
    }
   
    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")

    public void businessMethod() {
    }
    
}  
