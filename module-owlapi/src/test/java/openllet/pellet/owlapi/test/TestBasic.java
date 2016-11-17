package openllet.pellet.owlapi.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import openllet.owlapi.OWL;
import openllet.owlapi.OWLGenericTools;
import openllet.owlapi.OWLHelper;
import openllet.owlapi.OWLManagerGroup;
import openllet.owlapi.SWRL;
import openllet.shared.tools.Log;
import org.junit.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;

/**
 * Test basic of openllet-owlapi
 *
 * @since 2.6.0
 */
public class TestBasic
{
	static
	{
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");
		Log.setLevel(Level.WARNING, OWLGenericTools.class);
	}

	private final OWLClass ClsA = OWL.Class("#ClsA");
	private final OWLClass ClsB = OWL.Class("#ClsB");
	private final OWLClass ClsC = OWL.Class("#ClsC");
	private final OWLClass ClsD = OWL.Class("#ClsD");
	private final OWLClass ClsE = OWL.Class("#ClsE");
	private final OWLClass ClsF = OWL.Class("#ClsF");
	private final OWLClass ClsG = OWL.Class("#ClsG");
	private final OWLNamedIndividual Ind1 = OWL.Individual("#Ind1");
	private final OWLObjectProperty propA = OWL.ObjectProperty("#mimiroux");
	private final OWLDataProperty propB = OWL.DataProperty("#propB");
	private final SWRLVariable varA = SWRL.variable(IRI.create("#a"));

	@Test
	public void rule() throws OWLOntologyCreationException
	{
		try (final OWLManagerGroup group = new OWLManagerGroup())
		{
			final OWLOntologyID ontId = OWLHelper.getVersion(IRI.create("http://test.org#owlapi.tests"), 1.0);

			final OWLHelper owl = new OWLGenericTools(group, ontId, true);

			owl.declareIndividual(ClsA, Ind1);

			{
				final List<OWLClass> entities = owl.getReasoner().getTypes(Ind1).entities().collect(Collectors.toList());
				assertTrue(entities.size() == 2);
				assertTrue(entities.contains(ClsA));
				assertTrue(entities.contains(OWL.Thing));
			}

			owl.addAxiom(SWRL.rule(//
					SWRL.antecedent(SWRL.classAtom(ClsA, varA)), //
					SWRL.consequent(SWRL.classAtom(ClsB, varA))//
			));

			{
				final List<OWLClass> entities = owl.getReasoner().getTypes(Ind1).entities().collect(Collectors.toList());
				assertTrue(entities.size() == 3);
				assertTrue(entities.contains(ClsA));
				assertTrue(entities.contains(ClsB));
				assertTrue(entities.contains(OWL.Thing));
			}

			owl.addAxiom(SWRL.rule(//
					SWRL.antecedent(SWRL.classAtom(ClsB, varA)), //
					SWRL.consequent(SWRL.classAtom(ClsC, varA))//
			));

			{
				final List<OWLClass> entities = owl.getReasoner().getTypes(Ind1).entities().collect(Collectors.toList());
				assertTrue(entities.size() == 4);
				assertTrue(entities.contains(ClsA));
				assertTrue(entities.contains(ClsB));
				assertTrue(entities.contains(ClsC));
				assertTrue(entities.contains(OWL.Thing));
			}

			owl.addClass(Ind1, ClsD);

			owl.addAxiom(SWRL.rule(//
					SWRL.antecedent(SWRL.classAtom(OWL.and(ClsD, ClsC), varA)), //
					SWRL.consequent(SWRL.classAtom(ClsE, varA))//
			));

			{
				final List<OWLClass> entities = owl.getReasoner().getTypes(Ind1).entities().collect(Collectors.toList());
				assertTrue(entities.size() == 6);
				assertTrue(entities.contains(ClsA));
				assertTrue(entities.contains(ClsB));
				assertTrue(entities.contains(ClsC));
				assertTrue(entities.contains(ClsD));
				assertTrue(entities.contains(ClsE));
				assertTrue(entities.contains(OWL.Thing));
			}

			owl.addClass(Ind1, OWL.not(ClsF)); // Mark the negation to enforce the open world assertion.

			final SWRLRule D_and_NotF = SWRL.rule(//
					SWRL.antecedent(SWRL.classAtom(OWL.and(ClsD, OWL.not(ClsF)), varA)), //
					SWRL.consequent(SWRL.classAtom(ClsG, varA))//
			);

			{
				owl.addAxiom(D_and_NotF);
				final List<OWLClass> entities = owl.getReasoner().getTypes(Ind1).entities().collect(Collectors.toList());
				assertTrue(entities.contains(ClsG));
				owl.removeAxiom(D_and_NotF);
			}

			final SWRLRule D_and_F = SWRL.rule(//
					SWRL.antecedent(SWRL.classAtom(OWL.and(ClsD, ClsF), varA)), //
					SWRL.consequent(SWRL.classAtom(ClsG, varA))//
			);

			{
				owl.addAxiom(D_and_F);
				final List<OWLClass> entities = owl.getReasoner().getTypes(Ind1).entities().collect(Collectors.toList());
				assertFalse(entities.contains(ClsG));
				owl.removeAxiom(D_and_F);
			}
		}
	}

	@Test
	public void incrementalStorage() throws OWLOntologyCreationException
	{
		try (final OWLManagerGroup group = new OWLManagerGroup())
		{
			group.setOntologiesDirectory(new File("target"));
			group.getStorageManager();

			final OWLOntologyID ontId = OWLHelper.getVersion(IRI.create("http://test.org#owlapi.inc.storage"), 1.0);
			final OWLHelper owl = new OWLGenericTools(group, ontId, false);

			owl.addAxiom(OWL.declaration(ClsA));
			owl.addAxiom(OWL.declaration(ClsB));
			owl.addAxiom(OWL.propertyAssertion(Ind1, propA, Ind1));
			owl.addAxiom(OWL.propertyAssertion(Ind1, propB, OWL.constant(");alpha\"#\\\n \t\n\rbeta<xml></xml>")));

			// This test is good but a little too slow when building the project ten time a day.
			//			try
			//			{
			//				System.out.println("Waiting begin");
			//				Thread.sleep(65 * 1000);
			//				System.out.println("Waiting end");
			//			}
			//			catch (final Exception e)
			//			{
			//				e.printStackTrace();
			//			}
			group.flushIncrementalStorage();

			final File file = new File("target/test.org#owlapi.inc.storage-test.org#owlapi.inc.storage_1.0.owl");
			assertTrue(file.exists());
			file.delete();
			assertTrue(owl.getObject(Ind1, propA).get().getIRI().equals(Ind1.getIRI()));
		}
	}

	@Test
	public void testSubProperties() throws OWLOntologyCreationException
	{
		try (final OWLManagerGroup group = new OWLManagerGroup())
		{
			final OWLOntologyID ontId = OWLHelper.getVersion(IRI.create("http://test.org#owlapi.inc.properties"), 1.0);
			final OWLHelper owl = new OWLGenericTools(group, ontId, true);

			owl.addAxiom(OWL.subPropertyOf(OWL.ObjectProperty("#P2"), OWL.ObjectProperty("#P1"))); // p2 extends p1

			owl.addAxiom(OWL.propertyAssertion(OWL.Individual("#I1"), OWL.ObjectProperty("#P1"), OWL.Individual("#I2")));
			owl.addAxiom(OWL.propertyAssertion(OWL.Individual("#I3"), OWL.ObjectProperty("#P2"), OWL.Individual("#I4")));

			assertFalse(owl.getObject(OWL.Individual("#I1"), OWL.ObjectProperty("#P2")).isPresent());
			assertTrue(owl.getObject(OWL.Individual("#I3"), OWL.ObjectProperty("#P1")).get().equals(OWL.Individual("#I4")));
		}
	}
}
