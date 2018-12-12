package br.com.studies.algorithms.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import br.com.studies.algorithms.dp.Knapsack.Item;

public class KnapsackTests {

	@Test
	public void testKnapsackFewItems(){
		Knapsack k = new Knapsack();
		int size = 10;
		Item[] items = new Item[size];
		for (int i = 0; i < size; i++) {
			Item item = new Item();
			item.weight = i + 2;
			item.value = i;
			items[i] = item;
		}
		int max = k.execute(items, 10);
		assertEquals(8, max);
	}
	
	@Test
	public void testKnapsackThousand(){
		Knapsack k = new Knapsack();
		int size = 1000;
		Item[] items = new Item[size];
		for (int i = 0; i < size; i++) {
			Item item = new Item();
			item.weight = i + 2;
			item.value = i;
			items[i] = item;
		}
		int max = k.execute(items, 500);
		assertEquals(498, max);
	}
}
