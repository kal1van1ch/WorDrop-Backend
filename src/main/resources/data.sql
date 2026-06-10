-- A1
INSERT INTO words (word, translation, level) VALUES ('apple', 'яблоко', 'A1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('cat', 'кошка', 'A1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('dog', 'собака', 'A1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('book', 'книга', 'A1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('house', 'дом', 'A1') ON CONFLICT DO NOTHING;

-- A2
INSERT INTO words (word, translation, level) VALUES ('journey', 'путешествие', 'A2') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('furniture', 'мебель', 'A2') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('cloud', 'облако', 'A2') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('weather', 'погода', 'A2') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('bicycle', 'велосипед', 'A2') ON CONFLICT DO NOTHING;

-- B1
INSERT INTO words (word, translation, level) VALUES ('environment', 'окружающая среда', 'B1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('frequently', 'часто', 'B1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('improve', 'улучшать', 'B1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('government', 'правительство', 'B1') ON CONFLICT DO NOTHING;
INSERT INTO words (word, translation, level) VALUES ('experience', 'опыт', 'B1') ON CONFLICT DO NOTHING;