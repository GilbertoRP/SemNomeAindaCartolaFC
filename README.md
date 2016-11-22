# SemNomeAindaCartolaFC
Desenvolver uma aplicação que dada a lista de jogadores do CartolaFC, calcule a relação custo benefício de cada jogador e ordene seus jogadores com esta relação. Ainda será possível pesquisar jogadores pela taxa de crescimento a partir dos dados armazenados ao longo das rodadas

## definição

estrutura jogodor:
- name
- athlete_id
- points
- price
- variation
- mean
- orderingKey
- pos_id

chave de ordenação:
- mean * 3 + variation * 2 + price * 1

Manual de compilação está no arquivo: COMO_COMPILAR.TXT