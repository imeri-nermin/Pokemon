package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Controller {
    private final List<Pokemon> pokemonList = new ArrayList<>();
    private final List<Attack> attackList = new ArrayList<>();
    private final Map<String, Map<String, Double>> typeEffectiveness = new HashMap<>();

    public Controller() {
        loadPokemonFromCSV("pokemon.csv");
        loadAttacksFromCSV("attacks.csv");
        loadEffectivenessFromCSV("effectiveness.csv");
        assignRandomAttacksToPokemon();
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void loadPokemonFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line

            Random random = new Random();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String type1 = data[2];
                String type2 = data[3];
                int total = Integer.parseInt(data[4]);
                int hp = Integer.parseInt(data[5]);
                int attack = Integer.parseInt(data[6]);
                int defense = Integer.parseInt(data[7]);
                int spAtk = Integer.parseInt(data[8]);
                int spDef = Integer.parseInt(data[9]);
                int speed = Integer.parseInt(data[10]);
                int level = random.nextInt(100) + 1;

                Pokemon pokemon = new Pokemon(id, name, type1, type2, total, hp, attack, defense, spAtk, spDef, speed, level);
                pokemonList.add(pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAttacksFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String effect = data[2];
                String type = data[3];
                String kind = data[4];
                int power = Integer.parseInt(data[5]);
                String accuracy = data[6];
                int pp = Integer.parseInt(data[7]);

                Attack attack = new Attack(id, name, effect, type, kind, power, accuracy, pp);
                attackList.add(attack);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEffectivenessFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] types = null;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (types == null) {
                    types = data;
                } else {
                    String attackingType = data[0];
                    Map<String, Double> effectivenessMap = new HashMap<>();
                    for (int i = 1; i < data.length; i++) {
                        effectivenessMap.put(types[i], Double.parseDouble(data[i]));
                    }
                    typeEffectiveness.put(attackingType, effectivenessMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void assignRandomAttacksToPokemon() {
        Random random = new Random();

        for (Pokemon pokemon : pokemonList) {
            Attack attack1 = attackList.get(random.nextInt(attackList.size()));
            Attack attack2 = attackList.get(random.nextInt(attackList.size()));

            pokemon.setAttack1(attack1);
            pokemon.setAttack2(attack2);
        }
    }

    public int calculateDamage(Attack attack, Pokemon attacker, Pokemon defender) {
        double attackPower = attack.getPower();
        double attackerAttack = attacker.getAttack();
        double defenderDefense = defender.getDefense();
        double levelFactor = attacker.getLevel() / 50.0;
        double randomFactor = Math.random() * (1.0 - 0.85) + 0.85;
        double stabFactor = (attacker.getType1().equals(attack.getType()) || attacker.getType2().equals(attack.getType())) ? 1.5 : 1.0;
        double eff1 = typeEffectiveness.getOrDefault(attack.getType(), Collections.emptyMap()).getOrDefault(defender.getType1(), 1.0);
        double eff2 = typeEffectiveness.getOrDefault(attack.getType(), Collections.emptyMap()).getOrDefault(defender.getType2(), 1.0);

        return (int)((attackPower * (attackerAttack / defenderDefense) * levelFactor * randomFactor * stabFactor * eff1 * eff2) / 25.0);
    }
}
