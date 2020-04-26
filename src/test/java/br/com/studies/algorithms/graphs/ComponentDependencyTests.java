package br.com.studies.algorithms.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ComponentDependencyTests {

	@Test
	public void testSimpleDependencyGraph() {
		Map<String, List<String>> data = new HashMap<>();
		data.put("A", Arrays.asList("B", "C"));
		data.put("B", Arrays.asList("C"));
		data.put("C", Arrays.asList("E", "F", "G"));
		data.put("D", Arrays.asList("G", "H", "I"));
		data.put("E", Arrays.asList("H", "I"));
		data.put("F", Arrays.asList("H"));
		data.put("G", Arrays.asList("I"));
		data.put("H", Arrays.asList("I"));
		data.put("I", new ArrayList<>());
		ComponentDependency dep = new ComponentDependency(data);
		System.out.println(dep.findDependencies("A"));
	}
}
