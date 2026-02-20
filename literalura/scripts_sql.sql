-- ========================================
-- Scripts SQL para LiterAlura
-- ========================================

-- 1. Criar o banco de dados
CREATE DATABASE literalura_db;

-- 2. Conectar ao banco de dados
\c literalura_db;

-- 3. Verificar tabelas criadas (executar após rodar a aplicação)
\dt

-- 4. Consultas úteis

-- Ver todos os livros
SELECT * FROM livros ORDER BY titulo;

-- Ver todos os autores
SELECT * FROM autores ORDER BY nome;

-- Ver livros com seus autores
SELECT 
    l.titulo, 
    a.nome as autor, 
    l.idioma, 
    l.numero_downloads
FROM livros l
LEFT JOIN autores a ON l.autor_id = a.id
ORDER BY l.titulo;

-- Contar livros por idioma
SELECT idioma, COUNT(*) as quantidade
FROM livros
GROUP BY idioma
ORDER BY quantidade DESC;

-- Ver autores vivos em 1900
SELECT nome, ano_nascimento, ano_falecimento
FROM autores
WHERE ano_nascimento <= 1900 
  AND (ano_falecimento IS NULL OR ano_falecimento >= 1900)
ORDER BY nome;

-- Top 10 livros mais baixados
SELECT titulo, numero_downloads
FROM livros
ORDER BY numero_downloads DESC
LIMIT 10;

-- Autores com mais livros registrados
SELECT 
    a.nome, 
    COUNT(l.id) as total_livros
FROM autores a
LEFT JOIN livros l ON a.id = l.autor_id
GROUP BY a.nome
HAVING COUNT(l.id) > 0
ORDER BY total_livros DESC;

-- 5. Limpar dados (cuidado! Remove todos os registros)
-- TRUNCATE TABLE livros CASCADE;
-- TRUNCATE TABLE autores CASCADE;

-- 6. Deletar o banco de dados (cuidado!)
-- DROP DATABASE literalura_db;

-- 7. Verificar estrutura das tabelas
\d autores
\d livros
