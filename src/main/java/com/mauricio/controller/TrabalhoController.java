package com.mauricio.controller;

import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.mauricio.dao.DepartamentoDAO;
import com.mauricio.dao.PesquisadorDAO;
import com.mauricio.dao.ProjetoDAO;
import com.mauricio.dao.TrabalhoDAO;
import com.mauricio.dao.mongodb.DepartamentoMongoDBDAO;
import com.mauricio.dao.mongodb.PesquisadorMongoDBDAO;
import com.mauricio.dao.mongodb.ProjetoMongoDBDAO;
import com.mauricio.model.Departamento;
import com.mauricio.model.Pesquisador;
import com.mauricio.model.Projeto;
import com.mauricio.model.Trabalho;


public class TrabalhoController {

	private static Scanner sc1;
	
	public static void insert(TrabalhoDAO dao) {
		
		PesquisadorDAO daoPesquisador = new PesquisadorMongoDBDAO();
		DepartamentoDAO daoDepartamento = new DepartamentoMongoDBDAO();
		ProjetoDAO daoProjeto = new ProjetoMongoDBDAO();

		sc1 = new Scanner(System.in);
		Integer posicao;
		Trabalho entidade = new Trabalho();
		System.out.println("Digite as horas semanais do trabalho:");
		Integer horasSemanais = sc1.nextInt();
		entidade.setHorasSemanais(horasSemanais);
		
		List<Pesquisador> listPesquisador = daoPesquisador.findAll();
		List<Projeto> listProjeto = daoProjeto.findAll();
		int pos = 0;
		for(Pesquisador pesquisador: listPesquisador) {	
			System.out.println("Pesquisador "+(pos+1));
			System.out.println("Id: " + pesquisador.getId());
			System.out.println("Nome: " + pesquisador.getNome());
			System.out.println("Endereco: " + pesquisador.getEndereco());
			System.out.println("Sexo: " + pesquisador.getSexo());
			System.out.println("Salario: "+pesquisador.getSalario());
			System.out.println("Area de Atuacao: "+pesquisador.getAreaAtuacao());
			Departamento departamento = daoDepartamento.find(new ObjectId(pesquisador.getIdDepartamento().toString()));
			System.out.println("Departamento: "+departamento.getNome());
			pos++;
		}

		System.out.println("Digite o pesquisador:");
		posicao = sc1.nextInt();	
		entidade.setIdPesquisador(listPesquisador.get(posicao-1).getId());
		
		pos = 0;
		
		
		for(Projeto projeto : listProjeto) {	
			System.out.println("Projeto "+(pos+1));
			System.out.println("Id: " + projeto.getId());
			System.out.println("Nome: " + projeto.getNome());
			Departamento departamento = daoDepartamento.find(new ObjectId(projeto.getIdDepartamento().toString()));
			System.out.println("Departamento: "+departamento.getNome());
			pos++;
		
		}
		
		System.out.println("Digite o projeto:");
		posicao = sc1.nextInt();
		entidade.setIdProjeto(listProjeto.get(posicao-1).getId());
		
		dao.insert(entidade);
		dao.close();

	}

	public static void getAll(TrabalhoDAO dao) {
		List<Trabalho> list = dao.findAll();
		for(Trabalho entidade : list) {
			System.out.println("Id: " + entidade.getId());
			System.out.println("Horas semanais: " + entidade.getHorasSemanais());
			System.out.println("Id do pesquisador: " + entidade.getIdPesquisador());
			System.out.println("Id do projeto: " + entidade.getIdProjeto());
		}
		dao.close();
	}

	public static void delete(TrabalhoDAO dao) {
		int i = 0;
		sc1 = new Scanner(System.in);
		List<Trabalho> list = dao.findAll();
		
		for(Trabalho entidade : list) {
			System.out.println("\n Trabalho "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Horas semanais: " + entidade.getHorasSemanais());
			System.out.println("Id do pesquisador: " + entidade.getIdPesquisador());
			System.out.println("Id do projeto: " + entidade.getIdProjeto());
			
			i++;
		}
		
		System.out.println("Digite o id do Trabalho a ser excluido:");
		
		int posicao = sc1.nextInt();
		System.out.println(list.get(posicao-1).getId());
		
		dao.delete(new ObjectId(list.get(posicao-1).getId().toString()));
		dao.close();
	}
	
	public static void find(TrabalhoDAO dao) {
		
		PesquisadorDAO daoPesquisador = new PesquisadorMongoDBDAO();
		ProjetoDAO daoProjeto = new ProjetoMongoDBDAO();
		DepartamentoDAO daoDepartamento = new DepartamentoMongoDBDAO();
		
		int i = 0;
		sc1 = new Scanner(System.in);
		List<Trabalho> list = dao.findAll();
		
		for(Trabalho entidade : list) {
			System.out.println("\n Trabalho "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Horas semanais: " + entidade.getHorasSemanais());
			System.out.println("Id do pesquisador: " + entidade.getIdPesquisador());
			System.out.println("Id do projeto: " + entidade.getIdProjeto());
			
			i++;
		}
		
		System.out.println("Digite o id do Trabalho desejado:");
		int posicao = sc1.nextInt();
		
		Trabalho trabalho = dao.find(new ObjectId(list.get(posicao-1).getId().toString()));
		Pesquisador pesquisador = daoPesquisador.find(new ObjectId(trabalho.getIdPesquisador().toString()));
		Projeto projeto = daoProjeto.find(new ObjectId(trabalho.getIdProjeto().toString()));
		
		
		System.out.println("\n \n");
		System.out.println("Id: " + trabalho.getId());
		System.out.println("Horas semanais: " + trabalho.getHorasSemanais());
		System.out.println("Pesquisador: " + pesquisador.getNome());
		System.out.println("Area de Atuacao(Pesquisador): "+ pesquisador.getAreaAtuacao());
		System.out.println("Projeto: " + projeto.getNome());
		System.out.println("Periodo de tempo: " + projeto.getPeriodoTempo());
		Departamento departamento = daoDepartamento.find(new ObjectId(projeto.getIdDepartamento().toString()));
		System.out.println("Departamento: "+departamento.getNome());

		daoProjeto.close();
		daoPesquisador.close();
		daoDepartamento.close();
		dao.close();
		
	}
	

}
