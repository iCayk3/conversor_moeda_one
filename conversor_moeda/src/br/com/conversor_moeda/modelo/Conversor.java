package br.com.conversor_moeda.modelo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conversor {

    public Double converter(String moedaAConverter, Double valor) throws Exception {

        Double valorConvetido = null;

        switch(moedaAConverter){
            case "De Reais a Dólares":
                valorConvetido = valorCambio("BRL", "USD") * valor;
                break;
            case "De Reais a Euros":
                valorConvetido = valorCambio("BRL", "EUR") * valor;
                break;
            case  "De Reais a Libras Esterlinas":
                valorConvetido = valorCambio("BRL", "GBP") * valor;
                break;
            case  "De Reais a Peso argentino":
                valorConvetido = valorCambio("BRL", "ARS") * valor;
                break;
            case  "De Reais a Peso Chileno":
                valorConvetido = valorCambio("BRL", "CLP") * valor;
                break;
            case  "De Dólares a Reais":
                valorConvetido = valorCambio("USD", "BRL") * valor;
                break;
            case  "De Euros a Reais":
                valorConvetido = valorCambio("EUR", "BRL") * valor;
                break;
            case "De Libras Esterlinas a Reais":
                valorConvetido = valorCambio("GBP", "BRL") * valor;
                break;
            case  "De Peso argentino a Reais":
                valorConvetido = valorCambio("ARS", "BRL") * valor;
                break;
            case  "De Peso Chileno a Reais":
                valorConvetido = valorCambio("CLP", "BRL") * valor;
                break;
            default:
                valorConvetido = (double) 0;
        }
        return valorConvetido;
    }

    private static int codigoSucesso = 200;

    public Double valorCambio(String in, String out) throws Exception{
        String urlChamada = "https://api.invertexto.com/v1/currency/"
                + out + "_" + in + "?token=4847|IvOYCVRsUowPA16HN1TH1fQpyd0m8huk";

        try {

            URL url = new URL(urlChamada);
            //URLConnection connection = url.openConnection();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + connection.getResponseCode());

            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

            String valor = "";
            StringBuilder jsonValor = new StringBuilder();

            while((valor = br.readLine()) != null){
                jsonValor.append(valor);
            }

            JSONObject camada1Json = new JSONObject(jsonValor.toString());
            JSONObject camada2Json = (JSONObject) camada1Json.get(out+"_"+in);
            Float valorConsultado = camada2Json.getFloat("price");

            return (double) Math.round(valorConsultado * 100) / 100;


        } catch (Exception e){
            throw new Exception("ERRO " + e);
        }
    }
}
