package com.seuprojeto;

import com.seuprojeto.dao.EmpresaDAO;
import com.seuprojeto.model.Empresa;
import com.seuprojeto.model.PoliticaAntirracista;

import java.util.Arrays;

public class InicializarBanco {
    public static void main(String[] args) {
        EmpresaDAO empresaDAO = new EmpresaDAO();

        // Criar 5 empresas com 3 políticas cada
        for (int i = 1; i <= 5; i++) {
            Empresa empresa = new Empresa("Empresa " + i);

            PoliticaAntirracista politica1 = new PoliticaAntirracista("Diversidade e Inclusão", empresa);
            PoliticaAntirracista politica2 = new PoliticaAntirracista("Treinamento Antirracista", empresa);
            PoliticaAntirracista politica3 = new PoliticaAntirracista("Recrutamento Inclusivo", empresa);

            empresa.setPoliticas(Arrays.asList(politica1, politica2, politica3));

            // Salvar a empresa no banco de dados
            empresaDAO.salvar(empresa);
        }

        // Listar todas as empresas e suas políticas
        empresaDAO.listarEmpresasComPoliticas();
    }
}
