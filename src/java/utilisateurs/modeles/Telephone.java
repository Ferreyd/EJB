/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.modeles;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nicolas
 */
@Entity
public class Telephone {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String numTel;

    @ManyToMany
    @JoinTable(name = "UTILISATEUR_TELEPHONE",
            joinColumns = {
                @JoinColumn(name = "UTILISATEUR_PK", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "TELEPHONE_PK", referencedColumnName = "id")})
    private Set<Utilisateur> utilBelongsTo;

    public Telephone() {
    }

    public Telephone(String numTel) {
        this.numTel = numTel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

}
