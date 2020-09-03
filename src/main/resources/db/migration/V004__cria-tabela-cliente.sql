CREATE TABLE comentario (
	id bigint not null auto_increment,
    ordem_servico_id bigint not null,
    descricao text not null,
    data_envio datetime not null,

    primary key(id)
);

ALTER TABLE comentario add constraint fk_comentario_ordem_servico
foreign key (ordem_servico_id) references ordem_servico(id);