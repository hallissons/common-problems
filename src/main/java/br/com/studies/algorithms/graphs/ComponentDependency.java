package br.com.studies.algorithms.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ComponentDependency {

	private Map<String, List<String>> data;

	public ComponentDependency(Map<String, List<String>> data) {
		this.data = data;
	}

	public List<String> findDependencies(String c) {
		LinkedList<String> result = new LinkedList<>();
		Map<String, State> state = new HashMap<>();
		findAllDependencies(c, result, state);
		return result;
	}

	private void findAllDependencies(String c, LinkedList<String> result, Map<String, State> state) {
		List<String> deps = data.get(c);
		State st = state.get(c);
		if (st != null && State.BLACK.equals(st)) {
			return;
		}
		if (st != null && State.GRAY.equals(st)) {
			throw new RuntimeException("There's a cycle on: " + c);
		}
		state.put(c, State.GRAY);
		for (String dp : deps) {
			findAllDependencies(dp, result, state);
		}
		result.add(c);
		state.put(c, State.BLACK);
	}

	enum State {
		BLACK, GRAY
	}
}
