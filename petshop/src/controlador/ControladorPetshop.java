/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoPetshop;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.DaoPetshop;
import javax.swing.JOptionPane;
import modelo.PetshopModelo;
import tela.manutencao.ManutencaoPetshop;
public class ControladorPetshop {

    public static void inserir(ManutencaoPetshop man){
        PetshopModelo objeto = new PetshopModelo();
        objeto.setEndereco(man.jtfEndereco.getText());
        objeto.setNome(man.jtfNome.getText());
        objeto.setNumero(Integer.parseInt(man.jtfNumero.getText()));
        objeto.setAvaliacao(Integer.parseInt(man.jtfAvaliacao.getText()));
        boolean resultado = DaoPetshop.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

   public static void alterar(ManutencaoPetshop man){
        PetshopModelo objeto = new PetshopModelo();
        
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setEndereco(man.jtfEndereco.getText());
        objeto.setNumero(Integer.parseInt(man.jtfNumero.getText()));
        objeto.setAvaliacao(Integer.parseInt(man.jtfAvaliacao.getText()));
        
        boolean resultado = DaoPetshop.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    } 

     public static void excluir(ManutencaoPetshop man){
        PetshopModelo objeto = new PetshopModelo();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoPetshop.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Endereco");
        modelo.addColumn("Numero");
        modelo.addColumn("Avaliacao");
        List<PetshopModelo> resultados = DaoPetshop.consultar();
        for (PetshopModelo objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getEndereco());
            linha.add(objeto.getNumero());
            linha.add(objeto.getAvaliacao());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
}
