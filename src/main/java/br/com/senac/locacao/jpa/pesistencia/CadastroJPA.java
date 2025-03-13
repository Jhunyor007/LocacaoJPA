
package br.com.senac.locacao.jpa.pesistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CadastroJPA {
    
     public static void cadastrar(Cadastro cad) {
        EntityManager manager = JPAUtil.conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(cad);
            manager.getTransaction().commit();
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
    }
     
    public static List<Cadastro> listar() {
        List<Cadastro> lista = new ArrayList<Cadastro>();
        
        EntityManager manager = JPAUtil.conectar();
        try {
            Query consulta = manager.createQuery("SELECT c FROM cadastro c");
            lista = consulta.getResultList();
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
        return lista;
    }
    
     public static List<Cadastro> buscarNome (String filtro) {
            List<Cadastro> lista = new ArrayList<Cadastro>();

            EntityManager manager = JPAUtil.conectar();
            try {
                Query consulta = manager.createQuery("SELECT c FROM cadastro c WHERE (:nome is null OR c.nome LIKE :nome)");
                consulta.setParameter("nome", filtro.isEmpty() ? null : "%" + filtro + "%");
                lista = consulta.getResultList();
            } catch(Exception e) {
                manager.getTransaction().rollback();
            } finally {
                JPAUtil.desconectar();
            } 
            return lista;
        }
     
     public static void excluir (int id) {
            EntityManager manager = JPAUtil.conectar();
            try {
                manager.getTransaction().begin();
                Cadastro c = manager.find(Cadastro.class, id);
                if (c != null) {
                    manager.remove(c);
                }
                manager.getTransaction().commit();
            } catch(Exception e) {
                manager.getTransaction().rollback();
            } finally {
                JPAUtil.desconectar();
            } 
        }
}
