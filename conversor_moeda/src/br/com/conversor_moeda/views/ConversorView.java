package br.com.conversor_moeda.views;

import br.com.conversor_moeda.modelo.Conversor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConversorView extends JFrame {


    private JPanel contentPane;
    private Object inputValorAConverter;

    public ConversorView() throws Exception {
        try{
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 600, 400);
            contentPane = new JPanel();
            setLocationRelativeTo(null);

            Object[] optionMenuPrincipal = {"Conversor de moeda", "Conversor de Temperatura"};
            Object selectMenuPrincipal = JOptionPane.showInputDialog(
                    null, "Escolha uma opção", "Menu", JOptionPane.INFORMATION_MESSAGE,
                    null, optionMenuPrincipal, optionMenuPrincipal[0]
            );

            if(selectMenuPrincipal.equals("Conversor de moeda")){
                conversorMoedaView();
                Object continuarSOuN = JOptionPane.showConfirmDialog(null, "Deseja continuar?","", JOptionPane.YES_NO_CANCEL_OPTION);
                if(continuarSOuN.equals(0)){
                    new ConversorView();
                    dispose();
                }else if(continuarSOuN.equals(1)){
                    JOptionPane.showMessageDialog(null, "Programa finalizado");
                    System.exit(0);
                } else if (continuarSOuN.equals(2)) {
                    JOptionPane.showMessageDialog(null, "Programa concluido");
                    System.exit(0);
                }
            }else{
                conversorTemperaturaView();
            }

        }catch(NullPointerException e2){
            JOptionPane.showMessageDialog(null, "Programa finalizado");
            System.exit(0);
        }

    }

    private void conversorTemperaturaView() {

        Object[] optionMenuConversorTemperatura = {"Opcao 1", "Opcao2"};

        Object selectMenuConversorTemperatura = JOptionPane.showInputDialog(
                null, "Escolha uma moeda para qual desjea girar seu dinheiro",
                "Menu", JOptionPane.INFORMATION_MESSAGE,
                null, optionMenuConversorTemperatura, optionMenuConversorTemperatura[0]
        );
    }

    private void conversorMoedaView() throws Exception {
        try{
            Object[] optionMenuConversorMoeda = {
                    "De Reais a Dólares",
                    "De Reais a Euros",
                    "De Reais a Libras Esterlinas",
                    "De Reais a Peso argentino",
                    "De Reais a Peso Chileno",
                    "De Dólares a Reais",
                    "De Euros a Reais",
                    "De Libras Esterlinas a Reais",
                    "De Peso argentino a Reais",
                    "De Peso Chileno a Reais",
            };
            Object selectMenuConversorMoeda = JOptionPane.showInputDialog(
                    null, "Escolha uma moeda para qual desjea girar seu dinheiro",
                    "Menu", JOptionPane.INFORMATION_MESSAGE,
                    null, optionMenuConversorMoeda, optionMenuConversorMoeda[0]
            );

            inputValorAConverter = JOptionPane.showInputDialog(
                    null, "Insira um valor", "Input", JOptionPane.QUESTION_MESSAGE
            );

            Conversor conversor = new Conversor();
            Double valorConvertido = conversor.converter(selectMenuConversorMoeda.toString(), Double.parseDouble((String) inputValorAConverter));

            JOptionPane.showMessageDialog(null, "O da conversão é de R$ " + String.format("%.2f",valorConvertido));

        }catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Valor inválido!");
        }catch(NullPointerException e1){
            JOptionPane.showMessageDialog(null, "Programa finalizado");
            System.exit(0);
        }

    }
}
