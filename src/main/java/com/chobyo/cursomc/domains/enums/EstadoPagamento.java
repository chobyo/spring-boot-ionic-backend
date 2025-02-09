package com.chobyo.cursomc.domains.enums;

public enum EstadoPagamento {
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private Integer cod;
	private String descricao;

	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod)
	{
		if (cod == Character.MIN_VALUE) {
			return null;
		}
		
		for(EstadoPagamento c : EstadoPagamento.values())
		{
			if (cod == c.cod)
			{
				return c;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
