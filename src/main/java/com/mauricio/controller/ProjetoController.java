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
import com.mauricio.dao.mongodb.TrabalhoMongoDBDAO;
import com.mauricio.model.Departamento;
import com.mauricio.model.Dependente;
import com.mauricio.model.Pesquisador;
import com.mauricio.model.Projeto;
import com.mauricio.model.Trabalho;


public class ProjetoController {

	private static Scanner sc1;
	
	public static void insert(ProjetoDAO dao) {
		
		sc1 = new Scanner(System.in);
		
		Projeto entidade = new Projeto();
		DepartamentoDAO daoD = new DepartamentoMongoDBDAO();
		
		System.out.println("Digite o nome do Projeto:");
		String nome = sc1.nextLine();
		
		System.out.println("Digite o periodo de tempo do projeto:");
		String periodoTempo = sc1.nextLine();
		
		
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
		entidade.setPeriodoTempo(periodoTempo);

		dao.insert(entidade);
		dao.close();

	}

	public static void getAll(ProjetoDAO dao) {
		List<Projeto> list = dao.findAll();
		DepartamentoDAO daoD = new DepartamentoMongoDBDAO();
		for(Projeto entidade : list) {
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Periodo de tempo: " + entidade.getPeriodoTempo());
			Departamento departamento = daoD.find(new ObjectId(entidade.getIdDepartamento().toString()));
			System.out.println("Departamento: "+departamento.getNome());
		}
		dao.close();
	}

	public static void delete(ProjetoDAO dao) {
		int i = 0;
		sc1 = new Scanner(System.in);
		List<Projeto> list = dao.findAll();
		TrabalhoDAO daoTrabalho = new TrabalhoMongoDBDAO();
		
		for(Projeto entidade : list) {
			System.out.println("\n Projeto "+(i+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Periodo de tempo: " + entidade.getPeriodoTempo());
			i++;
		}
		
		System.out.println("Digite o id do Projeto a ser excluido:");
		
		int posicao = sc1.nextInt();
		System.out.println(list.get(posicao-1).getId());
		
		for(Trabalho trabalho : daoTrabalho.findAll()) {
			
			if(new ObjectId(list.get(posicao-1).getId().toString()).equals(trabalho.getIdProjeto())) {
			
				daoTrabalho.delete(new ObjectId(trabalho.getId().toString()));
				
			}
		}
		

		
		dao.delete(new ObjectId(list.get(posicao-1).getId().toString()));
		dao.close();
	}
	
	public static  void find(ProjetoDAO dao) {
			
		DepartamentoDAO daoDepartamento = new DepartamentoMongoDBDAO();
		PesquisadorDAO daoPesquisador = new PesquisadorMongoDBDAO();
		TrabalhoDAO daoTrabalho = new TrabalhoMongoDBDAO();
		
		int pos = 0;
		sc1 = new Scanner(System.in);
		List<Projeto> list = dao.findAll();
		for(Projeto entidade : list) {
			System.out.println("\n Projeto "+(pos+1)+"\n");
			System.out.println("Id: " + entidade.getId());
			System.out.println("Nome: " + entidade.getNome());
			System.out.println("Periodo de tempo: " + entidade.getPeriodoTempo());
			pos++;
		}
		
		System.out.println("Digite o Projeto desejado:");
		int posicao = sc1.nextInt();
		Projeto projeto = dao.find(new ObjectId(list.get(posicao-1).getId().toString()));
		Departamento departamento = daoDepartamento.find(new ObjectId(projeto.getIdDepartamento().toString()));
		
		System.out.println("Id: " + projeto.getId());
		System.out.println("Nome: " + projeto.getNome());
		System.out.println("Periodo de tempo: " + projeto.getPeriodoTempo());
		System.out.println("Departamento: " + departamento.getNome());
		
		System.out.println("\n \n Trabalhos");
		
		Pesquisador pesquisador;
		
		for(Trabalho trabalho : daoTrabalho.findAll()) {
			
			if(projeto.getId().equals(trabalho.getIdProjeto())) {
				
				System.out.println("\n \n");
				System.out.println("Id: " + trabalho.getId());
				System.out.println("Horas semanais: " + trabalho.getHorasSemanais());
				
				pesquisador = daoPesquisador.find(new ObjectId(trabalho.getIdPesquisador().toString()));
				
				System.out.println("Pesquisador: " + pesquisador.getNome());
				System.out.println("Area de Atuacao(Pesquisador): "+ pesquisador.getAreaAtuacao());
				Departamento departamento2 = daoDepartamento.find(new ObjectId(pesquisador.getIdDepartamento().toString()));
				System.out.println("Departamento: "+departamento2.getNome());
				
				
			}
		}
		
		daoDepartamento.close();
		daoPesquisador.close();
		daoTrabalho.close();
		dao.close();
		
	}

}
