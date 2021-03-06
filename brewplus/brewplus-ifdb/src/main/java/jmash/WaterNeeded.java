package jmash;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jdom.Element;

import jmash.component.JUnitSpinner;
import jmash.config.ConfigurationManager;
import jmash.config.bean.GeneralConfig;

public class WaterNeeded extends JInternalFrame {
	private static final long serialVersionUID = -5301195065823912614L;
	private static GeneralConfig generalConfig = ConfigurationManager.getIstance().getGeneralConfig();
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(WaterNeeded.class);
	
	private static final Double DEFAULT_TEMP_MASHIN= 68.0;

	private JPanel panelWaterNeeded;
	private JPanel panelSpecificheCotta;
	private JPanel panelDatiImpianto;
	private JPanel panelCalcoloPerdite;
	private JPanel panelCalcoloVolumi;
	private JPanel panelRisultati;
	private JLabel lblBatchSize;
	private JLabel lblGraniTotali;
	private JLabel lblOriginalGravity;
	private JLabel lblBoilTime;
	private JLabel lblAssorbimentoGraniEsausti;
	private JLabel lblRapportoAcquaGrani;
	private JLabel lblPercentualeEvaporazione;
	private JLabel lblContrazionePerRaffreddamento;
	private JCheckBox chckbxPerditeNelTrub;
	private JCheckBox chckbxDeadSpace;
	private JUnitSpinner spinnerBatchSize;
	private JUnitSpinner spinnerGraniTotali;
	private JUnitSpinner spinnerOriginalGravity;
	private JUnitSpinner spinnerBoilTime;
	private JUnitSpinner spinnerAssorbimentoGraniEsausti;
	private JUnitSpinner spinnerRapportoAcquaGrani;
	private JUnitSpinner spinnerPercentualeEvaporazione;
	private JUnitSpinner spinnerContrazionePerRaffreddamento;
	private JUnitSpinner spinnerPerditeNelTrub;
	private JUnitSpinner spinnerDeadSpace;
	private JLabel lblPerditaPerAssorbimento;
	private JLabel lblPerditaPerEvaporazione;
	private JLabel lblPerditaPerContrazione;
	private JLabel lblVolumeImpasto;
	private JLabel lblVolumeMostoPreboil;
	private JLabel lblOgPreboil;
	private JLabel lblVolumePostboil;
	private JLabel lblVolumePostraffreddamento;
	private JLabel lblAcquaDiMash;
	private JLabel lblStrikeWater;
	private JLabel lblAcquaDiSparge;
	private JLabel lblRatioEffettiva;
	private JUnitSpinner spinnerPerditaPerAssorbimento;
	private JUnitSpinner spinnerPerditaPerEvaporazione;
	private JUnitSpinner spinnerPerditaPerContrazione;
	private JUnitSpinner spinnerVolumeImpasto;
	private JUnitSpinner spinnerVolumeMostoPreBoil;
	private JUnitSpinner spinnerOGPreBoil;
	private JUnitSpinner spinnerVolumePostBoil;
	private JUnitSpinner spinnerVolumePostRaffreddamento;
	private JUnitSpinner spinnerAcquaMash;
	private JUnitSpinner spinnerStrikeWater;
	private JUnitSpinner spinnerRatioEffettiva;
	private JUnitSpinner spinnerAcquaSparge;
	private JUnitSpinner spinnerTotaleAcqua;
	private JLabel lblTotaleAcquaRichiesta;
	private JLabel lblVolumeRealeInFermentatore;
	private JUnitSpinner spinnerVolumeRealeInFermentatore;

	private Boolean biab = Boolean.FALSE;
	private GridBagConstraints gridBagConstraints_1;
	// OriginalGravity without late addiction fermentables
	private BigDecimal OriginalGravityIBU = new BigDecimal(0);
	private Double temperaturaMashIn = DEFAULT_TEMP_MASHIN;
	
	public WaterNeeded() {
		initComponents();
		setBorder(Utils.getDefaultBorder());

		spinnerBatchSize.setModel(generalConfig.getVolumeFin(), 0, 1000000, 0.5, "0.00", "WaterNeeded.BS");
		spinnerGraniTotali.setModel(0.0, 0.0, 1000000.0, 0.5, "0.000", "WaterNeeded.TotGrani");
		spinnerOriginalGravity.setModel(0.0, 0.0, 1000000.0, 1, "0", "WaterNeeded.OG");
		spinnerBoilTime.setModel(generalConfig.getBoilTime(), 0, 1000000, 0.5, "0.00", "WaterNeeded.BT");
		
		spinnerAssorbimentoGraniEsausti.setModel(generalConfig.getLitriPerKg(), 0, 1000000, 0.1, "0.00", null);
		spinnerDeadSpace.setModel(generalConfig.getDeadSpace(), 0.0, 1000000, 0.1, "0.00", null);
		spinnerRapportoAcquaGrani.setModel(generalConfig.getRapportoAcquaGrani(), 0.0, 1000000, 0.1, "0.00", null);
		spinnerPercentualeEvaporazione.setModel(generalConfig.getPercentualeEvaporazione(), 0.0, 100, 0.25, "0.00", null);
		spinnerContrazionePerRaffreddamento.setModel(generalConfig.getContrazionePerRaffreddamento(), 0, 100, 0.25, "0.00", null);
		spinnerPerditeNelTrub.setModel(generalConfig.getLostToTrub(), 0.0, 1000000, 0.1, "0.00", null);

		spinnerPerditaPerAssorbimento.setModel(Main.getFromCache("WaterNeeded.perditaPerAssorbimento", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PerdAss");
		spinnerPerditaPerEvaporazione.setModel(Main.getFromCache("WaterNeeded.perditaPerEvaporazione", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PerdEvap");
		spinnerPerditaPerContrazione.setModel(Main.getFromCache("WaterNeeded.perditaPerContrazione", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PerdContraz");

		spinnerVolumeImpasto.setModel(Main.getFromCache("WaterNeeded.PB", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PB");
		spinnerRatioEffettiva.setModel(Main.getFromCache("WaterNeeded.PB", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PB");
		spinnerVolumeMostoPreBoil.setModel(Main.getFromCache("WaterNeeded.PB", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PB");
		spinnerOGPreBoil.setModel(Main.getFromCache("WaterNeeded.pOG", 0.0), 0, 1000000, 0.5, "0", "WaterNeeded.pOG");
		spinnerVolumePostBoil.setModel(Main.getFromCache("WaterNeeded.PostB", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PostB");
		spinnerVolumePostRaffreddamento.setModel(Main.getFromCache("WaterNeeded.volumePostRaffreddamento", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumePostRaffreddamento");
		spinnerVolumeRealeInFermentatore.setModel(Main.getFromCache("WaterNeeded.volumeRealeInFermentatore", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeRealeInFermentatore");

		spinnerAcquaMash.setModel(Main.getFromCache("WaterNeeded.volumeMash", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeMash");
		spinnerStrikeWater.setModel(Main.getFromCache("WaterNeeded.StrikeWater", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.StrikeWater");
		spinnerAcquaSparge.setModel(Main.getFromCache("WaterNeeded.volumeSparge", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeSparge");
		spinnerTotaleAcqua.setModel(Main.getFromCache("WaterNeeded.volumeTotale", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeTotale");

		setBiab(generalConfig.getBiab(), false);
		setBackground(getBackground().darker());
		panelWaterNeeded.setBackground(panelWaterNeeded.getBackground().darker());

		calcolaQuantitaAcqua();

	}

	public WaterNeeded(double batchSize, double kg, double boilTime, double evap) {
		this();
		spinnerBatchSize.setDoubleValue(batchSize);
		spinnerGraniTotali.setDoubleValue(kg);
		spinnerBoilTime.setDoubleValue(boilTime);
		
		// spnEvaporation.setVolume(evap);

		calcolaQuantitaAcqua();
	}

	private void initComponents() {

		panelWaterNeeded = new JPanel(new GridBagLayout());

		setClosable(true);
		setIconifiable(true);
		setTitle("Acqua necessaria");

		initWaterNeededPanel();

		getContentPane().add(panelWaterNeeded, java.awt.BorderLayout.CENTER);

	}

	private void initWaterNeededPanel() {

		initPanelSpecificheCotta();
		initPanelDatiImpianto();

		initPanelCalcoloPerdite();
		initPanelCalcoloVolumi();
		initPanelRisultati();

	}

	private void initPanelSpecificheCotta() {

		GridBagConstraints gridBagConstraints;

		panelSpecificheCotta = new JPanel();
		panelSpecificheCotta.setBorder(javax.swing.BorderFactory.createTitledBorder("Specifiche della cotta"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 165, 78, 0, 0 };
		;
		panelSpecificheCotta.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelSpecificheCotta, gridBagConstraints);
		{
			lblBatchSize = new JLabel("Batch size");
			GridBagConstraints gbc_lblBatchSize = new GridBagConstraints();
			gbc_lblBatchSize.anchor = GridBagConstraints.EAST;
			gbc_lblBatchSize.insets = new Insets(0, 0, 5, 5);
			gbc_lblBatchSize.gridx = 0;
			gbc_lblBatchSize.gridy = 0;
			panelSpecificheCotta.add(lblBatchSize, gbc_lblBatchSize);
		}
		{
			spinnerBatchSize = new JUnitSpinner("L", 57);
			spinnerBatchSize.getSpinner().setPreferredSize(new Dimension(148, 22));
			spinnerBatchSize.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerBatchSize.setEnabled(false);
			GridBagConstraints gbc_spinnerBatchSize = new GridBagConstraints();
			gbc_spinnerBatchSize.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerBatchSize.gridx = 1;
			gbc_spinnerBatchSize.gridy = 0;
			panelSpecificheCotta.add(spinnerBatchSize, gbc_spinnerBatchSize);
		}
		{
			lblGraniTotali = new JLabel("Grani totali");
			GridBagConstraints gbc_lblGraniTotali = new GridBagConstraints();
			gbc_lblGraniTotali.anchor = GridBagConstraints.EAST;
			gbc_lblGraniTotali.insets = new Insets(0, 0, 5, 5);
			gbc_lblGraniTotali.gridx = 0;
			gbc_lblGraniTotali.gridy = 1;
			panelSpecificheCotta.add(lblGraniTotali, gbc_lblGraniTotali);
		}
		{
			spinnerGraniTotali = new JUnitSpinner("Kg", 57);
			spinnerGraniTotali.getSpinner().setPreferredSize(new Dimension(148, 22));
			spinnerGraniTotali.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerGraniTotali.setEnabled(false);
			GridBagConstraints gbc_spinnerGraniTotali = new GridBagConstraints();
			gbc_spinnerGraniTotali.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerGraniTotali.gridx = 1;
			gbc_spinnerGraniTotali.gridy = 1;
			panelSpecificheCotta.add(spinnerGraniTotali, gbc_spinnerGraniTotali);
		}
		{
			lblOriginalGravity = new JLabel("Original Gravity");
			GridBagConstraints gbc_lblOriginalGravity = new GridBagConstraints();
			gbc_lblOriginalGravity.anchor = GridBagConstraints.EAST;
			gbc_lblOriginalGravity.insets = new Insets(0, 0, 0, 5);
			gbc_lblOriginalGravity.gridx = 0;
			gbc_lblOriginalGravity.gridy = 2;
			panelSpecificheCotta.add(lblOriginalGravity, gbc_lblOriginalGravity);
		}
		{
			spinnerOriginalGravity = new JUnitSpinner("SG", 57);
			spinnerOriginalGravity.getSpinner().setPreferredSize(new Dimension(148, 22));
			spinnerOriginalGravity.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerOriginalGravity.setEnabled(false);
			GridBagConstraints gbc_spinnerOriginalGravity = new GridBagConstraints();
			gbc_spinnerOriginalGravity.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerOriginalGravity.gridx = 1;
			gbc_spinnerOriginalGravity.gridy = 2;
			panelSpecificheCotta.add(spinnerOriginalGravity, gbc_spinnerOriginalGravity);
		}
		{
			lblBoilTime = new JLabel("Boil Time");
			GridBagConstraints gbc_lblBoilTime = new GridBagConstraints();
			gbc_lblBoilTime.anchor = GridBagConstraints.EAST;
			gbc_lblBoilTime.insets = new Insets(0, 0, 5, 5);
			gbc_lblBoilTime.gridx = 0;
			gbc_lblBoilTime.gridy = 3;
			panelSpecificheCotta.add(lblBoilTime, gbc_lblBoilTime);
		}
		{
			spinnerBoilTime = new JUnitSpinner("min", 57);
			spinnerBoilTime.getSpinner().setPreferredSize(new Dimension(148, 22));
			spinnerBoilTime.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerBoilTime.setEnabled(false);
			GridBagConstraints gbc_spinnerBoilTime = new GridBagConstraints();
			gbc_spinnerBoilTime.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerBoilTime.gridx = 1;
			gbc_spinnerBoilTime.gridy = 3;
			panelSpecificheCotta.add(spinnerBoilTime, gbc_spinnerBoilTime);
		}

	}

	private void initPanelDatiImpianto() {

		panelDatiImpianto = new JPanel();
		panelDatiImpianto.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati impianto"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] {165, 78, 0, 0};
		panelDatiImpianto.setLayout(gridBagLayout);
		
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.fill = GridBagConstraints.BOTH;
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridheight = 2;
		gridBagConstraints_1.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelDatiImpianto, gridBagConstraints_1);
		{
			lblAssorbimentoGraniEsausti = new JLabel("Assorbimento grani esausti");
			GridBagConstraints gbc_lblAssorbimentoGraniEsausti = new GridBagConstraints();
			gbc_lblAssorbimentoGraniEsausti.anchor = GridBagConstraints.EAST;
			gbc_lblAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 5);
			gbc_lblAssorbimentoGraniEsausti.gridx = 0;
			gbc_lblAssorbimentoGraniEsausti.gridy = 0;
			panelDatiImpianto.add(lblAssorbimentoGraniEsausti, gbc_lblAssorbimentoGraniEsausti);
		}
		{
			spinnerAssorbimentoGraniEsausti = new JUnitSpinner("L/Kg", 57);
			spinnerAssorbimentoGraniEsausti.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerAssorbimentoGraniEsausti.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerAssorbimentoGraniEsausti = new GridBagConstraints();
			gbc_spinnerAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAssorbimentoGraniEsausti.gridx = 1;
			gbc_spinnerAssorbimentoGraniEsausti.gridy = 0;
			panelDatiImpianto.add(spinnerAssorbimentoGraniEsausti, gbc_spinnerAssorbimentoGraniEsausti);
		}
		{
			chckbxDeadSpace = new JCheckBox("Deadspace");
			chckbxDeadSpace.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					spinnerDeadSpace.setEnabled(chckbxDeadSpace.isSelected());
					calcolaQuantitaAcqua();
				}
			});
			GridBagConstraints gbc_chckbxDeadSpace = new GridBagConstraints();
			gbc_chckbxDeadSpace.anchor = GridBagConstraints.EAST;
			gbc_chckbxDeadSpace.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxDeadSpace.gridx = 0;
			gbc_chckbxDeadSpace.gridy = 1;
			panelDatiImpianto.add(chckbxDeadSpace, gbc_chckbxDeadSpace);
		}
		{
			spinnerDeadSpace = new JUnitSpinner("L", 57);
			spinnerDeadSpace.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerDeadSpace.setEnabled(false);
			spinnerDeadSpace.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerDeadSpace = new GridBagConstraints();
			gbc_spinnerDeadSpace.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerDeadSpace.gridx = 1;
			gbc_spinnerDeadSpace.gridy = 1;
			panelDatiImpianto.add(spinnerDeadSpace, gbc_spinnerDeadSpace);
		}
		{
			lblRapportoAcquaGrani = new JLabel("Rapporto acqua/grani");
			GridBagConstraints gbc_lblRapportoAcquaGrani = new GridBagConstraints();
			gbc_lblRapportoAcquaGrani.anchor = GridBagConstraints.EAST;
			gbc_lblRapportoAcquaGrani.insets = new Insets(0, 0, 5, 5);
			gbc_lblRapportoAcquaGrani.gridx = 0;
			gbc_lblRapportoAcquaGrani.gridy = 2;
			panelDatiImpianto.add(lblRapportoAcquaGrani, gbc_lblRapportoAcquaGrani);
		}
		{
			spinnerRapportoAcquaGrani = new JUnitSpinner("L/Kg", 57);
			spinnerRapportoAcquaGrani.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerRapportoAcquaGrani.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerRapportoAcquaGrani = new GridBagConstraints();
			gbc_spinnerRapportoAcquaGrani.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerRapportoAcquaGrani.gridx = 1;
			gbc_spinnerRapportoAcquaGrani.gridy = 2;
			panelDatiImpianto.add(spinnerRapportoAcquaGrani, gbc_spinnerRapportoAcquaGrani);
		}
		{
			lblPercentualeEvaporazione = new JLabel("Percentuale evaporazione");
			GridBagConstraints gbc_lblPercentualeEvaporazione = new GridBagConstraints();
			gbc_lblPercentualeEvaporazione.anchor = GridBagConstraints.EAST;
			gbc_lblPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_lblPercentualeEvaporazione.gridx = 0;
			gbc_lblPercentualeEvaporazione.gridy = 3;
			panelDatiImpianto.add(lblPercentualeEvaporazione, gbc_lblPercentualeEvaporazione);
		}
		{
			spinnerPercentualeEvaporazione = new JUnitSpinner("%", 57);
			spinnerPercentualeEvaporazione.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerPercentualeEvaporazione.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPercentualeEvaporazione = new GridBagConstraints();
			gbc_spinnerPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPercentualeEvaporazione.gridx = 1;
			gbc_spinnerPercentualeEvaporazione.gridy = 3;
			panelDatiImpianto.add(spinnerPercentualeEvaporazione, gbc_spinnerPercentualeEvaporazione);
		}
		{
			lblContrazionePerRaffreddamento = new JLabel("Contrazione per raffreddamento");
			GridBagConstraints gbc_lblContrazionePerRaffreddamento = new GridBagConstraints();
			gbc_lblContrazionePerRaffreddamento.anchor = GridBagConstraints.EAST;
			gbc_lblContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_lblContrazionePerRaffreddamento.gridx = 0;
			gbc_lblContrazionePerRaffreddamento.gridy = 4;
			panelDatiImpianto.add(lblContrazionePerRaffreddamento, gbc_lblContrazionePerRaffreddamento);
		}
		{
			spinnerContrazionePerRaffreddamento = new JUnitSpinner("%", 57);
			spinnerContrazionePerRaffreddamento.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerContrazionePerRaffreddamento.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerContrazionePerRaffreddamento = new GridBagConstraints();
			gbc_spinnerContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerContrazionePerRaffreddamento.gridx = 1;
			gbc_spinnerContrazionePerRaffreddamento.gridy = 4;
			panelDatiImpianto.add(spinnerContrazionePerRaffreddamento, gbc_spinnerContrazionePerRaffreddamento);
		}
		{
			chckbxPerditeNelTrub = new JCheckBox("Perdite nel Trub");
			chckbxPerditeNelTrub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					spinnerPerditeNelTrub.setEnabled(chckbxPerditeNelTrub.isSelected());
					calcolaQuantitaAcqua();
				}
			});
			GridBagConstraints gbc_chckbxPerditeNelTrub = new GridBagConstraints();
			gbc_chckbxPerditeNelTrub.anchor = GridBagConstraints.EAST;
			gbc_chckbxPerditeNelTrub.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxPerditeNelTrub.gridx = 0;
			gbc_chckbxPerditeNelTrub.gridy = 5;
			panelDatiImpianto.add(chckbxPerditeNelTrub, gbc_chckbxPerditeNelTrub);
		}
		{
			spinnerPerditeNelTrub = new JUnitSpinner("L", 57);
			spinnerPerditeNelTrub.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerPerditeNelTrub.setEnabled(false);
			spinnerPerditeNelTrub.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditeNelTrub = new GridBagConstraints();
			gbc_spinnerPerditeNelTrub.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditeNelTrub.gridx = 1;
			gbc_spinnerPerditeNelTrub.gridy = 5;
			panelDatiImpianto.add(spinnerPerditeNelTrub, gbc_spinnerPerditeNelTrub);
		}

	}

	private void initPanelCalcoloPerdite() {

		GridBagConstraints gridBagConstraints;

		panelCalcoloPerdite = new JPanel();
		panelCalcoloPerdite.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo delle perdite"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelCalcoloPerdite.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelCalcoloPerdite, gridBagConstraints);
		{
			lblPerditaPerAssorbimento = new JLabel("Perdita per assorbimento");
			GridBagConstraints gbc_lblPerditaPerAssorbimento = new GridBagConstraints();
			gbc_lblPerditaPerAssorbimento.anchor = GridBagConstraints.EAST;
			gbc_lblPerditaPerAssorbimento.insets = new Insets(0, 0, 5, 5);
			gbc_lblPerditaPerAssorbimento.gridx = 0;
			gbc_lblPerditaPerAssorbimento.gridy = 0;
			panelCalcoloPerdite.add(lblPerditaPerAssorbimento, gbc_lblPerditaPerAssorbimento);
		}
		{
			spinnerPerditaPerAssorbimento = new JUnitSpinner("L", 57);
			spinnerPerditaPerAssorbimento.setEnabled(false);
			spinnerPerditaPerAssorbimento.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditaPerAssorbimento = new GridBagConstraints();
			gbc_spinnerPerditaPerAssorbimento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditaPerAssorbimento.gridx = 1;
			gbc_spinnerPerditaPerAssorbimento.gridy = 0;
			panelCalcoloPerdite.add(spinnerPerditaPerAssorbimento, gbc_spinnerPerditaPerAssorbimento);
		}
		{
			lblPerditaPerEvaporazione = new JLabel("Perdita per evaporazione");
			GridBagConstraints gbc_lblPerditaPerEvaporazione = new GridBagConstraints();
			gbc_lblPerditaPerEvaporazione.anchor = GridBagConstraints.EAST;
			gbc_lblPerditaPerEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_lblPerditaPerEvaporazione.gridx = 0;
			gbc_lblPerditaPerEvaporazione.gridy = 1;
			panelCalcoloPerdite.add(lblPerditaPerEvaporazione, gbc_lblPerditaPerEvaporazione);
		}
		{
			spinnerPerditaPerEvaporazione = new JUnitSpinner("L", 57);;
			spinnerPerditaPerEvaporazione.setEnabled(false);
			spinnerPerditaPerEvaporazione.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditaPerEvaporazione = new GridBagConstraints();
			gbc_spinnerPerditaPerEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditaPerEvaporazione.gridx = 1;
			gbc_spinnerPerditaPerEvaporazione.gridy = 1;
			panelCalcoloPerdite.add(spinnerPerditaPerEvaporazione, gbc_spinnerPerditaPerEvaporazione);
		}
		{
			lblPerditaPerContrazione = new JLabel("Perdita per contrazione");
			GridBagConstraints gbc_lblPerditaPerContrazione = new GridBagConstraints();
			gbc_lblPerditaPerContrazione.anchor = GridBagConstraints.EAST;
			gbc_lblPerditaPerContrazione.insets = new Insets(0, 0, 0, 5);
			gbc_lblPerditaPerContrazione.gridx = 0;
			gbc_lblPerditaPerContrazione.gridy = 2;
			panelCalcoloPerdite.add(lblPerditaPerContrazione, gbc_lblPerditaPerContrazione);
		}
		{
			spinnerPerditaPerContrazione = new JUnitSpinner("L", 57);
			spinnerPerditaPerContrazione.setEnabled(false);
			spinnerPerditaPerContrazione.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditaPerContrazione = new GridBagConstraints();
			gbc_spinnerPerditaPerContrazione.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerPerditaPerContrazione.gridx = 1;
			gbc_spinnerPerditaPerContrazione.gridy = 2;
			panelCalcoloPerdite.add(spinnerPerditaPerContrazione, gbc_spinnerPerditaPerContrazione);
		}

	}

	private void initPanelCalcoloVolumi() {

		GridBagConstraints gridBagConstraints;

		panelCalcoloVolumi = new JPanel();
		panelCalcoloVolumi.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo dei volumi"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelCalcoloVolumi.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelCalcoloVolumi, gridBagConstraints);
		{
			lblRatioEffettiva = new JLabel("Rapporto acqua/grani effettivo");
			GridBagConstraints gbc_lblRatioEffettiva = new GridBagConstraints();
			gbc_lblRatioEffettiva.anchor = GridBagConstraints.EAST;
			gbc_lblRatioEffettiva.insets = new Insets(0, 0, 5, 5);
			gbc_lblRatioEffettiva.gridx = 0;
			gbc_lblRatioEffettiva.gridy = 0;
			panelCalcoloVolumi.add(lblRatioEffettiva, gbc_lblRatioEffettiva);
		}
		{
			spinnerRatioEffettiva = new JUnitSpinner("L/Kg", 57);
			spinnerRatioEffettiva.setEnabled(false);
			spinnerRatioEffettiva.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerRatioEffettiva = new GridBagConstraints();
			gbc_spinnerRatioEffettiva.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerRatioEffettiva.gridx = 1;
			gbc_spinnerRatioEffettiva.gridy = 0;
			panelCalcoloVolumi.add(spinnerRatioEffettiva, gbc_spinnerRatioEffettiva);
		}
		{
			lblStrikeWater = new JLabel("Temperatura di lancio");
			GridBagConstraints gbc_lblStrikeWater = new GridBagConstraints();
			gbc_lblStrikeWater.anchor = GridBagConstraints.EAST;
			gbc_lblStrikeWater.insets = new Insets(0, 0, 5, 5);
			gbc_lblStrikeWater.gridx = 0;
			gbc_lblStrikeWater.gridy = 1;
			panelCalcoloVolumi.add(lblStrikeWater, gbc_lblStrikeWater);
		}
		{
			spinnerStrikeWater = new JUnitSpinner("°C", 57);
			spinnerStrikeWater.setEnabled(false);
			spinnerStrikeWater.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerStrikeWater = new GridBagConstraints();
			gbc_spinnerStrikeWater.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerStrikeWater.gridx = 1;
			gbc_spinnerStrikeWater.gridy = 1;
			panelCalcoloVolumi.add(spinnerStrikeWater, gbc_spinnerStrikeWater);
		}
		{
			lblVolumeImpasto = new JLabel("Volume occupato dal mash");
			GridBagConstraints gbc_lblVolumeImpasto = new GridBagConstraints();
			gbc_lblVolumeImpasto.anchor = GridBagConstraints.EAST;
			gbc_lblVolumeImpasto.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumeImpasto.gridx = 0;
			gbc_lblVolumeImpasto.gridy = 2;
			panelCalcoloVolumi.add(lblVolumeImpasto, gbc_lblVolumeImpasto);
		}
		{
			spinnerVolumeImpasto = new JUnitSpinner("L", 57);
			spinnerVolumeImpasto.setEnabled(false);
			spinnerVolumeImpasto.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerVolumeImpasto = new GridBagConstraints();
			gbc_spinnerVolumeImpasto.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumeImpasto.gridx = 1;
			gbc_spinnerVolumeImpasto.gridy = 2;
			panelCalcoloVolumi.add(spinnerVolumeImpasto, gbc_spinnerVolumeImpasto);
		}
		{
			lblVolumeMostoPreboil = new JLabel("Volume mosto pre-boil");
			GridBagConstraints gbc_lblVolumeMostoPreboil = new GridBagConstraints();
			gbc_lblVolumeMostoPreboil.anchor = GridBagConstraints.EAST;
			gbc_lblVolumeMostoPreboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumeMostoPreboil.gridx = 0;
			gbc_lblVolumeMostoPreboil.gridy = 3;
			panelCalcoloVolumi.add(lblVolumeMostoPreboil, gbc_lblVolumeMostoPreboil);
		}
		{
			spinnerVolumeMostoPreBoil = new JUnitSpinner("L", 57);
			spinnerVolumeMostoPreBoil.setEnabled(false);
			spinnerVolumeMostoPreBoil.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerVolumeMostoPreBoil = new GridBagConstraints();
			gbc_spinnerVolumeMostoPreBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumeMostoPreBoil.gridx = 1;
			gbc_spinnerVolumeMostoPreBoil.gridy = 3;
			panelCalcoloVolumi.add(spinnerVolumeMostoPreBoil, gbc_spinnerVolumeMostoPreBoil);
		}
		{
			lblOgPreboil = new JLabel("OG pre-boil");
			GridBagConstraints gbc_lblOgPreboil = new GridBagConstraints();
			gbc_lblOgPreboil.anchor = GridBagConstraints.EAST;
			gbc_lblOgPreboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblOgPreboil.gridx = 0;
			gbc_lblOgPreboil.gridy = 4;
			panelCalcoloVolumi.add(lblOgPreboil, gbc_lblOgPreboil);
		}
		{
			spinnerOGPreBoil = new JUnitSpinner("SG", 57);
			spinnerOGPreBoil.setEnabled(false);
			spinnerOGPreBoil.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerOGPreBoil = new GridBagConstraints();
			gbc_spinnerOGPreBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerOGPreBoil.gridx = 1;
			gbc_spinnerOGPreBoil.gridy = 4;
			panelCalcoloVolumi.add(spinnerOGPreBoil, gbc_spinnerOGPreBoil);
		}
		{
			lblVolumePostboil = new JLabel("Volume post-boil");
			GridBagConstraints gbc_lblVolumePostboil = new GridBagConstraints();
			gbc_lblVolumePostboil.anchor = GridBagConstraints.EAST;
			gbc_lblVolumePostboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumePostboil.gridx = 0;
			gbc_lblVolumePostboil.gridy = 5;
			panelCalcoloVolumi.add(lblVolumePostboil, gbc_lblVolumePostboil);
		}
		{
			spinnerVolumePostBoil = new JUnitSpinner("L", 57);
			spinnerVolumePostBoil.setEnabled(false);
			spinnerVolumePostBoil.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerVolumePostBoil = new GridBagConstraints();
			gbc_spinnerVolumePostBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumePostBoil.gridx = 1;
			gbc_spinnerVolumePostBoil.gridy = 5;
			panelCalcoloVolumi.add(spinnerVolumePostBoil, gbc_spinnerVolumePostBoil);
		}
		{
			lblVolumePostraffreddamento = new JLabel("Volume post-raffreddamento");
			GridBagConstraints gbc_lblVolumePostraffreddamento = new GridBagConstraints();
			gbc_lblVolumePostraffreddamento.anchor = GridBagConstraints.EAST;
			gbc_lblVolumePostraffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumePostraffreddamento.gridx = 0;
			gbc_lblVolumePostraffreddamento.gridy = 6;
			panelCalcoloVolumi.add(lblVolumePostraffreddamento, gbc_lblVolumePostraffreddamento);
		}
		{
			spinnerVolumePostRaffreddamento = new JUnitSpinner("L", 57);
			spinnerVolumePostRaffreddamento.setEnabled(false);
			spinnerVolumePostRaffreddamento.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerVolumePostRaffreddamento = new GridBagConstraints();
			gbc_spinnerVolumePostRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumePostRaffreddamento.gridx = 1;
			gbc_spinnerVolumePostRaffreddamento.gridy = 6;
			panelCalcoloVolumi.add(spinnerVolumePostRaffreddamento, gbc_spinnerVolumePostRaffreddamento);
		}
		{
			lblVolumeRealeInFermentatore = new JLabel("Volume reale in fermentatore");
			GridBagConstraints gbc_lblVolumeRealeInFermentatore = new GridBagConstraints();
			gbc_lblVolumeRealeInFermentatore.anchor = GridBagConstraints.EAST;
			gbc_lblVolumeRealeInFermentatore.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeRealeInFermentatore.gridx = 0;
			gbc_lblVolumeRealeInFermentatore.gridy = 7;
			panelCalcoloVolumi.add(lblVolumeRealeInFermentatore, gbc_lblVolumeRealeInFermentatore);
		}
		{
			spinnerVolumeRealeInFermentatore = new JUnitSpinner("L", 57);
			spinnerVolumeRealeInFermentatore.setPreferredSize(new Dimension(148, 22));
			spinnerVolumeRealeInFermentatore.setEnabled(false);
			GridBagConstraints gbc_spinnerVolumeRealeInFermentatore = new GridBagConstraints();
			gbc_spinnerVolumeRealeInFermentatore.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerVolumeRealeInFermentatore.gridx = 1;
			gbc_spinnerVolumeRealeInFermentatore.gridy = 7;
			panelCalcoloVolumi.add(spinnerVolumeRealeInFermentatore, gbc_spinnerVolumeRealeInFermentatore);
		}

	}

	private void initPanelRisultati() {

		GridBagConstraints gridBagConstraints;

		panelRisultati = new JPanel();
		panelRisultati.setBorder(javax.swing.BorderFactory.createTitledBorder("Totale acqua richiesta"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelRisultati.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelRisultati, gridBagConstraints);
		{
			lblAcquaDiMash = new JLabel("Acqua di mash");
			GridBagConstraints gbc_lblAcquaDiMash = new GridBagConstraints();
			gbc_lblAcquaDiMash.anchor = GridBagConstraints.EAST;
			gbc_lblAcquaDiMash.insets = new Insets(0, 0, 5, 5);
			gbc_lblAcquaDiMash.gridx = 0;
			gbc_lblAcquaDiMash.gridy = 0;
			panelRisultati.add(lblAcquaDiMash, gbc_lblAcquaDiMash);
		}
		{
			spinnerAcquaMash = new JUnitSpinner("L", 57);
			spinnerAcquaMash.setEnabled(false);
			spinnerAcquaMash.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerAcquaMash = new GridBagConstraints();
			gbc_spinnerAcquaMash.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAcquaMash.gridx = 1;
			gbc_spinnerAcquaMash.gridy = 0;
			panelRisultati.add(spinnerAcquaMash, gbc_spinnerAcquaMash);
		}
		{
			lblAcquaDiSparge = new JLabel("Acqua di sparge");
			GridBagConstraints gbc_lblAcquaDiSparge_1 = new GridBagConstraints();
			gbc_lblAcquaDiSparge_1.anchor = GridBagConstraints.EAST;
			gbc_lblAcquaDiSparge_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblAcquaDiSparge_1.gridx = 0;
			gbc_lblAcquaDiSparge_1.gridy = 1;
			panelRisultati.add(lblAcquaDiSparge, gbc_lblAcquaDiSparge_1);
		}
		{
			spinnerAcquaSparge = new JUnitSpinner("L", 57);
			spinnerAcquaSparge.setEnabled(false);
			spinnerAcquaSparge.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerAcquaDiSparge = new GridBagConstraints();
			gbc_spinnerAcquaDiSparge.anchor = GridBagConstraints.ABOVE_BASELINE;
			gbc_spinnerAcquaDiSparge.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAcquaDiSparge.gridx = 1;
			gbc_spinnerAcquaDiSparge.gridy = 1;
			panelRisultati.add(spinnerAcquaSparge, gbc_spinnerAcquaDiSparge);
		}
		{
			lblTotaleAcquaRichiesta = new JLabel("Totale acqua richiesta");
			GridBagConstraints gbc_lblTotaleAcquaRichiesta = new GridBagConstraints();
			gbc_lblTotaleAcquaRichiesta.anchor = GridBagConstraints.EAST;
			gbc_lblTotaleAcquaRichiesta.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotaleAcquaRichiesta.gridx = 0;
			gbc_lblTotaleAcquaRichiesta.gridy = 2;
			panelRisultati.add(lblTotaleAcquaRichiesta, gbc_lblTotaleAcquaRichiesta);
		}
		{
			spinnerTotaleAcqua = new JUnitSpinner("L", 57);
			spinnerTotaleAcqua.setEnabled(false);
			spinnerTotaleAcqua.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerTotaleAcqua = new GridBagConstraints();
			gbc_spinnerTotaleAcqua.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerTotaleAcqua.gridx = 1;
			gbc_spinnerTotaleAcqua.gridy = 2;
			panelRisultati.add(spinnerTotaleAcqua, gbc_spinnerTotaleAcqua);
		}

	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(ChangeListener.class, listener);
	}

	public void removeChangeListener(ChangeListener listener) {
		listenerList.remove(ChangeListener.class, listener);
	}

	protected void fireStateChanged(ActionEvent actionEvent) {
		ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
		if (listeners != null && listeners.length > 0) {
			ChangeEvent evt = new ChangeEvent(actionEvent);
			for (ChangeListener listener : listeners) {
				listener.stateChanged(evt);
			}
		}
	}

	public void setBatchSize(double batchSize) {
		this.spinnerBatchSize.setDoubleValue(batchSize);
	}

	public void setTotGrani(double totGrani) {
		this.spinnerGraniTotali.setDoubleValue(totGrani);
	}

	public void setOriginalGravity(double originalGravity) {
		double sg = (originalGravity * 1000) - 1000;
		this.spinnerOriginalGravity.setDoubleValue(sg);
	}
	
	public void setBoilTime(double boilTime){
		this.spinnerBoilTime.setDoubleValue(boilTime);
	}

	public void calcolaQuantitaAcqua() {
		calcolaQuantitaAcqua(false);
	}
	
	public void calcolaQuantitaAcqua(boolean fireEvent) {

		// dati in input
		double batchSize = this.spinnerBatchSize.getDoubleValue();
		double totGrani = this.spinnerGraniTotali.getDoubleValue();
		
		@SuppressWarnings("unused")
		double originalGravity = this.spinnerOriginalGravity.getDoubleValue();
		
		double boilTime = this.spinnerBoilTime.getDoubleValue();
		double assorbimentoGraniEsausti = this.spinnerAssorbimentoGraniEsausti.getDoubleValue();
		double rapportoAcquaGrani = this.spinnerRapportoAcquaGrani.getDoubleValue();
		double percentualeEvaporazione = this.spinnerPercentualeEvaporazione.getDoubleValue();
		double contrazioneRaffreddamento = this.spinnerContrazionePerRaffreddamento.getDoubleValue();
		double perditeNelTrub = this.chckbxPerditeNelTrub.isSelected() ? this.spinnerPerditeNelTrub.getDoubleValue() : 0.0;
		double DeadSpace = this.chckbxDeadSpace.isSelected() ? this.spinnerDeadSpace.getDoubleValue() : 0.0;
		this.spinnerPerditeNelTrub.setEnabled(this.chckbxPerditeNelTrub.isSelected());
		this.spinnerDeadSpace.setEnabled(this.chckbxDeadSpace.isSelected());

		// inizio calcoli
		double perditeAssorbimento = totGrani * assorbimentoGraniEsausti;
		double volumePostRaffreddamento = batchSize;
		double volumeRealeInFermentaore = volumePostRaffreddamento - perditeNelTrub;
		double volumePostBoil = volumePostRaffreddamento * (1.0 + (contrazioneRaffreddamento / 100.0));
		double volumeMostoPreBoil = volumePostBoil * (1.0 + ((percentualeEvaporazione / 100.0)*(boilTime / 60.00)));
		double ogPreBoil = (batchSize * OriginalGravityIBU.intValue()) / volumeMostoPreBoil;
		double perditaContrazione = volumePostRaffreddamento * (contrazioneRaffreddamento / 100.0);
		double perditaEvaporazione = volumePostBoil * (percentualeEvaporazione / 100.0) * (boilTime / 60.0);
		double acquaTotale = volumeMostoPreBoil + perditeAssorbimento;
		double acquaMash = !biab ? (totGrani * rapportoAcquaGrani) + DeadSpace : acquaTotale;
		double acquaSparge = acquaTotale - acquaMash;
		double volumeImpasto = acquaMash + 0.67 * totGrani;
		double strikeWater = ((0.41 / (acquaMash / totGrani)) * ( getTemperaturaMashIn() - getTemperaturaGrani() )) + getTemperaturaMashIn();
		double ratioEffettiva = acquaMash / totGrani;
		
		
		spinnerPerditaPerAssorbimento.setDoubleValue(perditeAssorbimento);
		spinnerPerditaPerEvaporazione.setDoubleValue(perditaEvaporazione);
		spinnerPerditaPerContrazione.setDoubleValue(perditaContrazione);

		spinnerRatioEffettiva.setDoubleValue(ratioEffettiva);
		spinnerVolumeImpasto.setDoubleValue(volumeImpasto);
		spinnerStrikeWater.setDoubleValue(strikeWater);
		spinnerVolumeMostoPreBoil.setDoubleValue(volumeMostoPreBoil);
		spinnerOGPreBoil.setDoubleValue(ogPreBoil);
		spinnerVolumePostBoil.setDoubleValue(volumePostBoil);
		spinnerVolumePostRaffreddamento.setDoubleValue(volumePostRaffreddamento);
		spinnerVolumeRealeInFermentatore.setDoubleValue(volumeRealeInFermentaore);

		spinnerAcquaMash.setDoubleValue(acquaMash);
		spinnerAcquaSparge.setDoubleValue(acquaSparge);
		spinnerTotaleAcqua.setDoubleValue(acquaTotale);
		
		if (fireEvent)
		{
			fireStateChanged(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
		}
	}

	public void fromXml(Element e) {

		if (e.getAttribute("BatchSize") != null)
			spinnerBatchSize.setDoubleValue(new Double(e.getAttribute("BatchSize").getValue()));
		if (e.getAttribute("GraniTotali") != null)
			spinnerGraniTotali.setDoubleValue(new Double(e.getAttribute("GraniTotali").getValue()));
		if (e.getAttribute("OriginalGravity") != null)
			spinnerOriginalGravity.setDoubleValue(new Double(e.getAttribute("OriginalGravity").getValue()));
		if (e.getAttribute("BoilTime") != null)
			spinnerBoilTime.setDoubleValue(new Double(e.getAttribute("BoilTime").getValue()));
		if (e.getAttribute("AssorbimentoGraniEsausti") != null)
			spinnerAssorbimentoGraniEsausti
					.setDoubleValue(new Double(e.getAttribute("AssorbimentoGraniEsausti").getValue()));

		if (e.getAttribute("HasPerditeNelTrub") != null)
			chckbxPerditeNelTrub.setSelected(new Boolean(e.getAttribute("HasPerditeNelTrub").getValue()));
		if (e.getAttribute("PerditeNelTrub") != null)
			spinnerPerditeNelTrub.setDoubleValue(new Double(e.getAttribute("PerditeNelTrub").getValue()));
		if (e.getAttribute("HasDeadSpace") != null)
			chckbxDeadSpace.setSelected(new Boolean(e.getAttribute("HasDeadSpace").getValue()));
		if (e.getAttribute("DeadSpace") != null)
			spinnerDeadSpace.setDoubleValue(new Double(e.getAttribute("DeadSpace").getValue()));
		if (e.getAttribute("RapportoAcquaGrani") != null)
			spinnerRapportoAcquaGrani
					.setDoubleValue(new Double(e.getAttribute("RapportoAcquaGrani").getValue()));

		if (e.getAttribute("PercentualeEvaporazione") != null)
			spinnerPercentualeEvaporazione
					.setDoubleValue(new Double(e.getAttribute("PercentualeEvaporazione").getValue()));
		if (e.getAttribute("ContrazionePerRaffreddamento") != null)
			spinnerContrazionePerRaffreddamento
					.setDoubleValue(new Double(e.getAttribute("ContrazionePerRaffreddamento").getValue()));

		if (e.getAttribute("PerditaPerAssorbimento") != null)
			spinnerPerditaPerAssorbimento
					.setDoubleValue(new Double(e.getAttribute("PerditaPerAssorbimento").getValue()));
		if (e.getAttribute("PerditaPerEvaporazione") != null)
			spinnerPerditaPerEvaporazione
					.setDoubleValue(new Double(e.getAttribute("PerditaPerEvaporazione").getValue()));
		if (e.getAttribute("PerditaPerContrazione") != null)
			spinnerPerditaPerContrazione
					.setDoubleValue(new Double(e.getAttribute("PerditaPerContrazione").getValue()));

		if (e.getAttribute("RatioEffettiva") != null)
			spinnerRatioEffettiva
					.setDoubleValue(new Double(e.getAttribute("RatioEffettiva").getValue()));
		if (e.getAttribute("VolumeImpasto") != null)
			spinnerVolumeImpasto
					.setDoubleValue(new Double(e.getAttribute("VolumeImpasto").getValue()));
		if (e.getAttribute("StrikeWater") != null)
			spinnerStrikeWater
					.setDoubleValue(new Double(e.getAttribute("StrikeWater").getValue()));
		if (e.getAttribute("VolumeMostoPreBoil") != null)
			spinnerVolumeMostoPreBoil
					.setDoubleValue(new Double(e.getAttribute("VolumeMostoPreBoil").getValue()));
		if (e.getAttribute("OGPreBoil") != null)
			spinnerOGPreBoil.setDoubleValue(new Double(e.getAttribute("OGPreBoil").getValue()));
		if (e.getAttribute("VolumePostBoil") != null)
			spinnerVolumePostBoil.setDoubleValue(new Double(e.getAttribute("VolumePostBoil").getValue()));
		if (e.getAttribute("VolumePostRaffreddamento") != null)
			spinnerVolumePostRaffreddamento
					.setDoubleValue(new Double(e.getAttribute("VolumePostRaffreddamento").getValue()));
		if (e.getAttribute("VolumeRealeInFermentatore") != null)
			spinnerVolumeRealeInFermentatore
					.setDoubleValue(new Double(e.getAttribute("VolumeRealeInFermentatore").getValue()));

		if (e.getAttribute("AcquaMash") != null)
			spinnerAcquaMash.setDoubleValue(new Double(e.getAttribute("AcquaMash").getValue()));
		if (e.getAttribute("AcquaSparge") != null)
			spinnerAcquaSparge.setDoubleValue(new Double(e.getAttribute("AcquaSparge").getValue()));
		if (e.getAttribute("TotaleAcqua") != null)
			spinnerTotaleAcqua.setDoubleValue(new Double(e.getAttribute("TotaleAcqua").getValue()));

	}

	public Element toXml() {
		Element e = new Element("water");

		e.setAttribute("BatchSize", "" + spinnerBatchSize.getDoubleValue());
		e.setAttribute("GraniTotali", "" + spinnerGraniTotali.getDoubleValue());
		e.setAttribute("OriginalGravity", "" + spinnerOriginalGravity.getDoubleValue());
		e.setAttribute("BoilTime", "" + spinnerBoilTime.getDoubleValue());
		e.setAttribute("AssorbimentoGraniEsausti", "" + spinnerAssorbimentoGraniEsausti.getDoubleValue());

		e.setAttribute("HasPerditeNelTrub", "" + chckbxPerditeNelTrub.isSelected());
		e.setAttribute("PerditeNelTrub", "" + spinnerPerditeNelTrub.getDoubleValue());
		e.setAttribute("HasDeadSpace", "" + chckbxDeadSpace.isSelected());
		e.setAttribute("DeadSpace", "" + spinnerDeadSpace.getDoubleValue());
		e.setAttribute("RapportoAcquaGrani", "" + spinnerRapportoAcquaGrani.getDoubleValue());

		e.setAttribute("PercentualeEvaporazione", "" + spinnerPercentualeEvaporazione.getDoubleValue());
		e.setAttribute("ContrazionePerRaffreddamento",
				"" + spinnerContrazionePerRaffreddamento.getDoubleValue());
		e.setAttribute("PercentualeEvaporazione", "" + spinnerPercentualeEvaporazione.getDoubleValue());

		e.setAttribute("PerditaPerAssorbimento", "" + spinnerPerditaPerAssorbimento.getDoubleValue());
		e.setAttribute("PerditaPerEvaporazione", "" + spinnerPerditaPerEvaporazione.getDoubleValue());
		e.setAttribute("PerditaPerContrazione", "" + spinnerPerditaPerContrazione.getDoubleValue());
		
		e.setAttribute("RatioEffettiva", "" + spinnerRatioEffettiva.getDoubleValue());
		e.setAttribute("VolumeImpasto", "" + spinnerVolumeImpasto.getDoubleValue());
		e.setAttribute("StrikeWater", "" + spinnerStrikeWater.getDoubleValue());
		e.setAttribute("VolumeMostoPreBoil", "" + spinnerVolumeMostoPreBoil.getDoubleValue());
		e.setAttribute("OGPreBoil", "" + spinnerOGPreBoil.getDoubleValue());
		e.setAttribute("VolumePostBoil", "" + spinnerVolumePostBoil.getDoubleValue());
		e.setAttribute("VolumePostRaffreddamento", "" + spinnerVolumePostRaffreddamento.getDoubleValue());
		e.setAttribute("VolumeRealeInFermentatore", "" + spinnerVolumeRealeInFermentatore.getDoubleValue());

		e.setAttribute("AcquaMash", "" + spinnerAcquaMash.getDoubleValue());
		e.setAttribute("AcquaSparge", "" + spinnerAcquaSparge.getDoubleValue());
		e.setAttribute("TotaleAcqua", "" + spinnerTotaleAcqua.getDoubleValue());

		return e;
	}

	public double getTotWater() {
		return spinnerTotaleAcqua.getDoubleValue();
	}
	
	public double getRapportoAcquaGrani() {
		return spinnerRapportoAcquaGrani.getDoubleValue();
	}
	
	public double getMashVolume() {
		return spinnerAcquaMash.getDoubleValue();
	}

	public double getSpargeVolume() {
		return spinnerAcquaSparge.getDoubleValue();
	}

	public double getOGPreBoil() {
		return spinnerOGPreBoil.getDoubleValue();
	}

	public double getOriginalGravity() {
		return spinnerOriginalGravity.getDoubleValue();
	}

	public double getRatioEffettiva() {
		return spinnerRatioEffettiva.getDoubleValue();
	}
	public double getVolumeImpasto() {
		return spinnerVolumeImpasto.getDoubleValue();
	}
	
	public double getStrikeWater() {
		return spinnerStrikeWater.getDoubleValue();
	}
	
	public double getVolumeMostoPreBoil() {
		return spinnerVolumeMostoPreBoil.getDoubleValue();
	}

	public double getVolumePostBoil() {
		return spinnerVolumePostBoil.getDoubleValue();
	}

	public double getVolumePostRaffreddamento() {
		return spinnerVolumePostRaffreddamento.getDoubleValue();
	}

	public double getVolumeRealeInFermentatore() {
		return spinnerVolumeRealeInFermentatore.getDoubleValue();
	}

	public Boolean getBiab() {
		return biab;
	}

	public void setBiab(Boolean biab) {
		setBiab(biab, true);
	}
	
	public void setBiab(Boolean biab, boolean fireEvent) {
		this.biab = biab;
		
		lblRapportoAcquaGrani.setVisible(!biab);
		spinnerRapportoAcquaGrani.setVisible(!biab);
		
		lblAcquaDiMash.setVisible(!biab);
		spinnerAcquaMash.setVisible(!biab);

		lblAcquaDiSparge.setVisible(!biab);
		spinnerAcquaSparge.setVisible(!biab);
		
		calcolaQuantitaAcqua(fireEvent);
		
	}

    public BigDecimal getOriginalGravityIBU() {
        return OriginalGravityIBU;
    }

    public void setOriginalGravityIBU(BigDecimal originalGravityIBU) {
        OriginalGravityIBU = originalGravityIBU;
    }
    
    
    private Double getTemperaturaGrani()
    {
    	return generalConfig.getTempGrani();
    }
    
    private Double getTemperaturaMashIn()
    {
    	return temperaturaMashIn;
    }
    
    public void setTemperaturaMashIn(Double temperaturaMashIn) {
		this.temperaturaMashIn = temperaturaMashIn != null ? temperaturaMashIn : DEFAULT_TEMP_MASHIN;
		calcolaQuantitaAcqua();
	}

}
