package P1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import engine.GameConfig;


class ValoresInvalidosException extends Exception {
    public ValoresInvalidosException(String mensagem) {
        super(mensagem);

    }

}

class ArquivoIncompletoException extends Exception {
    public ArquivoIncompletoException(String mensagem) {
        super(mensagem);

    }

}


public class P1 {

    public static GameConfig lerConfiguracaoTexto(String nomeArquivo) 
            throws IOException, ValoresInvalidosException, ArquivoIncompletoException {
        
        BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
        

        String linha1 = leitor.readLine();
        String linha2 = leitor.readLine();
        String linha3 = leitor.readLine();
        
        leitor.close(); 

        if (linha1 == null || linha2 == null || linha3 == null) {
            throw new ArquivoIncompletoException("Erro: O arquivo de texto nao tem as 3 linhas necessarias.");
        }


        String[] dadosLinha1 = linha1.split(",");
        String[] dadosLinha2 = linha2.split(",");
        String[] dadosLinha3 = linha3.split(",");


        int largura = Integer.parseInt(dadosLinha1[0]);
        int altura = Integer.parseInt(dadosLinha1[1]);


        int vidasPlayer = Integer.parseInt(dadosLinha2[0]);
        float velocidadePlayer = Float.parseFloat(dadosLinha2[1]);


        float taxaSpawnInimigos = Float.parseFloat(dadosLinha3[0]);
        int chanceAsteroide = Integer.parseInt(dadosLinha3[1]);
        int vidaAsteroide = Integer.parseInt(dadosLinha3[2]);
        int vidaAlien = Integer.parseInt(dadosLinha3[3]);


        if (largura <= 0 || altura <= 0 || vidasPlayer <= 0) {
            throw new ValoresInvalidosException("Erro: Configuracoes com valores menores ou iguais a zero não são válidas.");
        }

        GameConfig config = new GameConfig(largura, altura, vidasPlayer, velocidadePlayer, 
                                           taxaSpawnInimigos, chanceAsteroide, vidaAsteroide, vidaAlien);
        
        return config;
    }


    public static void main(String[] args) throws Exception {

        System.out.println("- P1 iniciado -");

        String arquivoTXT = "config.txt";
        System.out.println("Lendo dados do arquivo texto: " + arquivoTXT);
        GameConfig configuracaoDoJogo = lerConfiguracaoTexto(arquivoTXT);


        String arquivoBinario = "config_jogo.dat";
        System.out.println("Salvando dados em formato binario: " + arquivoBinario);
        
        FileOutputStream arquivoSaida = new FileOutputStream(arquivoBinario);
        ObjectOutputStream gravadorBinario = new ObjectOutputStream(arquivoSaida);
        
        gravadorBinario.writeObject(configuracaoDoJogo);
        

        gravadorBinario.close();
        arquivoSaida.close();

        System.out.println("- Arquivo binário gerado -");

    }

}