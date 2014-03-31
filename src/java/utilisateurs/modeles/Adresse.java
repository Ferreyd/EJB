/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.modeles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Nicolas
 */
@Entity
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Size(min = 1)
    private String ville;
    @Pattern(regexp = "[0-9]{5}") // On accepte les codes postaux du type "06410"  
    private String codePostal;
    private String numRue;

    @OneToMany(mappedBy = "adresse")
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    public void addUtilisateur(Utilisateur u) {
        utilisateurs.add(u);
    }

    public void removeUtilisateur(Utilisateur u) {
        utilisateurs.remove(u);
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public Adresse() {
    }

    public Adresse(String ville, String codePostal) {
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

}
