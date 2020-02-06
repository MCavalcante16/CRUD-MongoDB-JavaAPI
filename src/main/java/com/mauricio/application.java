package com.mauricio;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.mauricio.controller.DepartamentoController;
import com.mauricio.controller.DependenteController;
import com.mauricio.controller.FuncionarioLimpezaController;
import com.mauricio.controller.PesquisadorController;
import com.mauricio.controller.ProjetoController;
import com.mauricio.controller.SecretarioController;
import com.mauricio.controller.TrabalhoController;
import com.mauricio.dao.UserDAO;
import com.mauricio.dao.mongodb.DepartamentoMongoDBDAO;
import com.mauricio.dao.mongodb.DependenteMongoDBDAO;
import com.mauricio.dao.mongodb.FuncionarioLimpezaMongoDBDAO;
import com.mauricio.dao.mongodb.PesquisadorMongoDBDAO;
import com.mauricio.dao.mongodb.ProjetoMongoDBDAO;
import com.mauricio.dao.mongodb.SecretarioMongoDBDAO;
import com.mauricio.dao.mongodb.TrabalhoMongoDBDAO;
import com.mauricio.dao.mongodb.UserMongoDBDAO;

public class application {

	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 

	public static void main(String[] args) {
		
		

		Scanner sc1;
		Boolean sair = true;
		Integer opcao = null;

    	

    	printMenu(); 
    	
    	while(sair) {
    		sc1 = new Scanner(System.in); 
    		
    			System.out.println(" 23 - Ver o menu novamente");
        		opcao = sc1.nextInt();
        		
        		switch(opcao) {
        		
        			case 1:
        				
        				System.out.println("1 - Adicionar Pesquisador");
        				System.out.println("2 - Adicionar Secretario");
        				System.out.println("3 - Adicionar Funcionario de Limpeza");
        				opcao = sc1.nextInt();
        				
        				if(opcao == 1) {
        						
        					PesquisadorController.insert(new PesquisadorMongoDBDAO());
        					
        				}else if(opcao == 2) {
        					
        					SecretarioController.insert(new SecretarioMongoDBDAO());
        					
        				}else if(opcao == 3){
        					
        					FuncionarioLimpezaController.insert(new FuncionarioLimpezaMongoDBDAO());
        					
        				}else {
        					System.out.println("Opcao invalida.");
        				}
        				
        				break;
        				
        			case 2:
        					
        				System.out.println("1 - Buscar Pesquisador");
        				System.out.println("2 - Buscar Secretario");
        				System.out.println("3 - Buscar Funcionario de Limpeza");
        				opcao = sc1.nextInt();
        				
        				if(opcao == 1) {
        						
        					PesquisadorController.find(new PesquisadorMongoDBDAO());
        					
        				}else if(opcao == 2) {
        					
        					SecretarioController.find(new SecretarioMongoDBDAO());
        					
        				}else if(opcao == 3){
        					
        					FuncionarioLimpezaController.find(new FuncionarioLimpezaMongoDBDAO());
        					
        				}else {
        					System.out.println("Opcao invalida.");
        				}
        				
            			break;
            			
        			case 3:
        				
        				System.out.println("1 - Deletar Pesquisador");
        				System.out.println("2 - Deletar Secretario");
        				System.out.println("3 - Deletar Funcionario de Limpeza");
        				opcao = sc1.nextInt();
        				
        				if(opcao == 1) {
        						
        					PesquisadorController.delete(new PesquisadorMongoDBDAO());
        					
        				}else if(opcao == 2) {
        					
        					SecretarioController.delete(new SecretarioMongoDBDAO());
        					
        				}else if(opcao == 3){
        					
        					FuncionarioLimpezaController.delete(new FuncionarioLimpezaMongoDBDAO());
        					
        				}else {
        					System.out.println("Opcao invalida.");
        				}
        				
            			break;
            			
        			case 4:
        				
        				System.out.println("1 - Buscar Pesquisadores");
        				System.out.println("2 - Buscar Secretarios");
        				System.out.println("3 - Buscar Funcionarios de Limpeza");
        				opcao = sc1.nextInt();
        				
        				if(opcao == 1) {
        					
        					PesquisadorController.getAll(new PesquisadorMongoDBDAO());
        					
        				}else if(opcao == 2) {
        					
        					SecretarioController.getAll(new SecretarioMongoDBDAO());
        					
        				}else if(opcao == 3){
        					
        					FuncionarioLimpezaController.getAll(new FuncionarioLimpezaMongoDBDAO());
        					
        				}else {
        					System.out.println("Opcao invalida.");
        				}
        			
            			break;
            			
        			case 5:
        				
        				DepartamentoController.insert(new DepartamentoMongoDBDAO());
        				
            			break;
            			
        			case 6:
            			break;
            			
        			case 7:
        				DepartamentoController.delete(new DepartamentoMongoDBDAO());
        				
            			break;
            			
        			case 8:
        				DepartamentoController.getAll(new DepartamentoMongoDBDAO());
        				
            			break;
            			
        			case 9:
        				ProjetoController.insert(new ProjetoMongoDBDAO());
            			break;
            			
        			case 10:
        				ProjetoController.find(new ProjetoMongoDBDAO());
        				break;
        				
        			case 11:
        				ProjetoController.delete(new ProjetoMongoDBDAO());
        				break;
        				
        			case 12:
        				ProjetoController.getAll(new ProjetoMongoDBDAO());
        				break;
        			case 13:
        				TrabalhoController.insert(new TrabalhoMongoDBDAO());
        				break;
        				
        			case 14:
        				TrabalhoController.find(new TrabalhoMongoDBDAO());
        				break;
        				
        			case 15:
        				TrabalhoController.delete(new TrabalhoMongoDBDAO());
        				break;
        				
        			case 16:
        				
        				TrabalhoController.getAll(new TrabalhoMongoDBDAO());
        				
        				break;
        			case 17:
        				DependenteController.insert(new DependenteMongoDBDAO());
        				break;
        			case 18:
        				DependenteController.find(new DependenteMongoDBDAO());
        				break;
        			case 19:
        				DependenteController.delete(new DependenteMongoDBDAO());
        				break;
        			case 20:
        				DependenteController.getAll(new DependenteMongoDBDAO());
        				break;
        			case 21:	
        				System.out.println("\t Atualizar \n");
        				System.out.println("1 - Pesquisador");
        				System.out.println("2 - Secretario");
        				System.out.println("3 - Funcionario de Limpeza");
        				System.out.println("4 - Departamento");
        				System.out.println("5 - Dependente");
        				System.out.println("6 - Trabalho");
        				System.out.println("7 - Projeto \n");
        				opcao = sc1.nextInt();
        				
        				if(opcao == 1) {
        						
        					
        				}else if(opcao == 2) {
        					
        					
        				}else if(opcao == 3){
        					
        					
        				}else if(opcao == 4){
        					
        					
        				}else if(opcao == 5){
        					
        					
        				}else if(opcao == 6){
        					
        					
        				}else if(opcao == 7){
        					
        					
        				}
        				
        				break;
        			case 22:
        				sair = false;
        				break;
        			case 23:
        				printMenu();
        				break;
        				
        			default:
        				System.out.println("Opção inválida.");
        				break;
        				
        		
        		}
    	
    	}

	}
	
	public static void printMenu() {
		System.out.println(" 1  - Adicionar Funcionario");
		System.out.println(" 2  - Buscar Funcionario");
		System.out.println(" 3  - Deletar Funcionario");
		System.out.println(" 4  - Buscar Funcionarios");
		System.out.println(" 5  - Adicionar Departamento");
		System.out.println(" 6  - Buscar Departamento");
		System.out.println(" 7  - Deletar Departamento");
		System.out.println(" 8  - Buscar Departamentos");
		System.out.println(" 9  - Adicionar Projeto");
		System.out.println(" 10 - Buscar Projeto");
		System.out.println(" 11 - Deletar Projeto");
		System.out.println(" 12 - Buscar Projetos");
		System.out.println(" 13 - Adicionar Trabalho");
		System.out.println(" 14 - Buscar Trabalho");
		System.out.println(" 15 - Deletar Trabalho");
		System.out.println(" 16 - Buscar Trabalhos");
		System.out.println(" 17 - Adicionar Dependente");
		System.out.println(" 18 - Buscar Dependente");
		System.out.println(" 19 - Deletar Dependente");
		System.out.println(" 20 - Buscar Dependentes");
		System.out.println(" 21 - Sair do sistema");
	}
	
	

	
	
	
	
	
	
//public static void main(String[] args) {
		
		//DependenteDAO dao = new DependenteMongoDBDAO();
	
		//PesquisadorDAO dao = new PesquisadorMongoDBDAO();
	
//		FuncionarioLimpezaDAO dao = new FuncionarioLimpezaMongoDBDAO();
	
		
//		SecretarioDAO dao = new SecretarioMongoDBDAO();
	
//		ProjetoDAO dao1 = new ProjetoMongoDBDAO();
//		TrabalhoDAO dao2 = new TrabalhoMongoDBDAO();
//	
//		dao01Inserir(dao1, dao2);
//		 dao02findById(dao);
//		 dao03Update(dao);
//		 dao04DeleteById(dao);
//		 dao05DeleteUser(dao);
//		dao06FindAll(dao1, dao2);
//		dao1.close();
//		dao2.close();
//		
//		System.out.println("Bye, Mongo DAO");
	
	
//	}

}
