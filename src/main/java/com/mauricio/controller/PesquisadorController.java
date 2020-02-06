package com.mauricio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.mauricio.dao.DepartamentoDAO;
import com.mauricio.dao.DependenteDAO;
import com.mauricio.dao.PesquisadorDAO;
import com.mauricio.dao.TrabalhoDAO;
import com.mauricio.dao.mongodb.DepartamentoMongoDBDAO;
import com.mauricio.dao.mongodb.DependenteMongoDBDAO;
import com.mauricio.dao.mongodb.TrabalhoMongoDBDAO;
import com.mauricio.model.Departamento;
import com.mauricio.model.Dependente;
import com.mauricio.model.Pesquisador;
import com.mauricio.model.Trabalho;

public class PesquisadorController {
	
private static Scanner sc1;
	
	public static void insert(PesquisadorDAO dao) {
		
		DepartamentoDAO daoD = new DepartamentoMongoDBDAO();
		
		sc1 = new Scanner(System.in);
		
		Pesquisador entidade = new Pesquisador();
		
		System.out.println("Digite seu nome :");
		String nome = sc1.nextLine();
		
		System.out.println("Digite seu endereço :");
		String endereco = sc1.nextLine();

		
		System.out.println("Digite seu sexo :");
		String sexo = sc1.nextLine();
		
		
		System.out.println("Digite sua data de nascimento:");
		LocalDate nascimento =  LocalDate.parse(sc1.nextLine(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
		
		System.out.println("Digite o seu salario:");
		Double salario = sc1.nextDouble();
		sc1.nextLine();
		
		System.out.println("Digite a Area de atuacao:");
		String atuacao = sc1.nextLine();
		
	
		
		List<Departamento> list = daoD.findAll();
		int pos = 0;
		for(Departamento dep : list) {	
			System.out.println("Departamento "+(pos+1));
			System.out.println("Id: " + dep.getId());
			System.out.println("Nome: " + dep.getNome());
			pos++;
		
		}
		daoD.close();
		
		System.out.println("Digite o departamento:");
		Integer posicao = sc1.nextInt();
		
		
		entidade.setIdDepartamento(list.get(posicao-1).getId());
		
		entidade.setNome(nome);
		entidade.setEndereco(endereco);
		entidade.setSexo(sexo);
		entidade.setDataNascimento(nascimento);
		entidade.setSalario(salario);
		entidade.setAreaAtuacao(atuacao);
		
		dao.insert(entidade);
		dao.close();

	}

	public static void getAll(PesquisadorDAO dao) {
		List<Pesquisador> list = dao.findAll();
		
		DepartamentoDAO daoD = new DepartamentoMongoDBDAO();

		for(Pesquisador entidade : list) {
			System.out.println("\n \nId: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Endereco: " + entidade.getEndereco());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Salario: "+entidade.getSalario());
			System.out.println("Area de Atuacao: "+entidade.getAreaAtuacao());
			Departamento departamento = daoD.find(new ObjectId(entidade.getIdDepartamento().toString()));
			System.out.println("Departamento: "+departamento.getNome());
		}
		dao.close();
	}

	public static void delete(PesquisadorDAO dao) {
		
		DependenteDAO daoDependente = new DependenteMongoDBDAO();
		TrabalhoDAO daoTrabalho = new TrabalhoMongoDBDAO();
		List<Dependente> dependentes = daoDependente.findAll();
		
		int i = 0;
		sc1 = new Scanner(System.in);
		List<Pesquisador> list = dao.findAll();
		for(Pesquisador entidade : list) {
			System.out.println("\n Pesquisador "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Endereco: " + entidade.getEndereco());
			System.out.println("Sexo: " + entidade.getSexo());
			System.out.println("Salario: "+entidade.getSalario());
			System.out.println("Area de Atuacao: "+entidade.getAreaAtuacao());
			i++;
		}
		
		System.out.println("Digite o id do Pesquisador a ser excluido:");
		
		int posicao = sc1.nextInt();		
		
		for(Dependente dependente : dependentes) {
			
			if(new ObjectId(list.get(posicao-1).getId().toString()).equals(dependente.getIdFuncionario())) {
				
				daoDependente.delete(new ObjectId(dependente.getId().toString()));
			
			}
		}
		
		for(Trabalho trabalho : daoTrabalho.findAll()) {
			
			if(new ObjectId(list.get(posicao-1).getId().toString()).equals(trabalho.getIdPesquisador())) {
			
				daoTrabalho.delete(new ObjectId(trabalho.getId().toString()));
				
			}
		}
		
		
		dao.delete(new ObjectId(list.get(posicao-1).getId().toString()));
		
		
		dao.close();
		daoDependente.close();
	}
	
	public static void find(PesquisadorDAO dao) {
		DependenteDAO daoDependente = new DependenteMongoDBDAO();
		DepartamentoDAO daoDepartamento = new DepartamentoMongoDBDAO();
		
		int pos = 0;
		sc1 = new Scanner(System.in);
		List<Pesquisador> list = dao.findAll();
		List<Dependente> dependentes = daoDependente.findAll();
		
		for(Pesquisador entidade : list) {
			System.out.println("\n Pesquisador "+(pos+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			pos++;
		}
		
		
		
		
		System.out.println("Digite o Pesquisador desejado:");
		int posicao = sc1.nextInt();
		Pesquisador pesquisador = dao.find(new ObjectId(list.get(posicao-1).getId().toString()));
		Departamento departamento = daoDepartamento.find(new ObjectId(pesquisador.getIdDepartamento().toString()));


		System.out.println("Id: " + pesquisador.getId());
		System.out.println("Nome: " + pesquisador.getNome());
		System.out.println("Endereco: " + pesquisador.getEndereco());
		System.out.println("Sexo: " + pesquisador.getSexo());
		System.out.println("Salario: "+pesquisador.getSalario());
		System.out.println("Area de Atuacao: "+pesquisador.getAreaAtuacao());
		System.out.println("Departamento: "+departamento.getNome());
		
		System.out.println("\n \t Dependentes");
		for(Dependente dependente : dependentes) {
			
			if(pesquisador.getId().equals(dependente.getIdFuncionario())) {
				
				
				System.out.println("\n\nId: " + dependente.getId());
				System.out.println("Nome: " + dependente.getNome());
				System.out.println("Sexo: " + dependente.getSexo());
				System.out.println("Data de Nascimento: " + dependente.getDataNascimento());
				System.out.println("Grau de Parentesco: " + dependente.getGrauParentesco());
			}
		}
		
		daoDepartamento.close();
		daoDependente.close();
		dao.close();
		
	}
	
}
