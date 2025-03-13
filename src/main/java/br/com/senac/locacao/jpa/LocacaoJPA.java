

package br.com.senac.locacao.jpa;

import br.com.senac.locacao.jpa.pesistencia.Cadastro;
import br.com.senac.locacao.jpa.pesistencia.CadastroJPA;
import br.com.senac.locacao.jpa.pesistencia.JPAUtil;
import java.util.List;

public class LocacaoJPA {

    public static void main(String[] args) {
       
        Cadastro cad = new Cadastro(0,"Helena","000.004.005-06","Aruja","Ap2","3",1050,"Pix");
        
        CadastroJPA.cadastrar(cad);
        
        List<Cadastro> lista = CadastroJPA.listar();
        
        for(int i = 0; lista.size() > i; i++)
        {
            System.out.println(lista.get(i).getId()+"-"+lista.get(i).getNome());
        }
    }
}
