package test;

import datos.PersonaDAO;
import domain.Persona;

import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {

        var personaDao = new PersonaDAO();


        Persona personaNueva = new Persona("Lucas","Urriste","lukeu2002@mail.com","2223524735");
//        personaDao.insertar(personaNueva);
//        Persona personaUpdate = new Persona(2,"Lucas","Urriste","lurriste@mail.com", "123499887");
//        personaDao.actualizar(personaUpdate);

        var personaEliminar = new Persona(1);
        personaDao.eliminar(personaEliminar);

        List<Persona> personas = personaDao.seleccionar();
        for (Persona persona:personas){
            System.out.println("Persona" + persona);
        }
    }
}
