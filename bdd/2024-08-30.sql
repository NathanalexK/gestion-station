CREATE OR REPLACE  VIEW PREVISION_COMPLET_CPL (ID, DESIGNATION, IDCAISSE, IDCAISSELIB, IDVENTEDETAIL, IDVENTE, IDVENTELIB, IDVIREMENT, DEBIT, CREDIT, DATY, ETAT, IDOP, IDOPLIB, IDORIGINE, IDDEVISE, IDDEVISELIB, TAUX, IDTIERS, COMPTE, EFFECTIFDEBIT, EFFECTIFCREDIT, DEPENSEECART, RECETTEECART, IDFACTURE) AS 
  SELECT 
	p.ID,p.DESIGNATION,p.IDCAISSE,p.IDCAISSELIB,p.IDVENTEDETAIL,p.IDVENTE,p.IDVENTELIB,p.IDVIREMENT,p.DEBIT,p.CREDIT,p.DATY,p.ETAT,p.IDOP,p.IDOPLIB,p.IDORIGINE,p.IDDEVISE,p.IDDEVISELIB,p.TAUX,p.IDTIERS,p.COMPTE ,
	nvl(pe.effectifDeb,0) AS effectifDebit,
	nvl(pe.effectifCred,0) AS effectifCredit,
	p.debit - nvl(pe.effectifDeb,0) AS depenseEcart,
	p.credit - nvl(pe.effectifCred,0) AS recetteEcart,
	p.idfacture
FROM PREVISION_CPL p 
LEFT join PrevisionAvecMvtCaisse pe on pe.id=p.id ;
