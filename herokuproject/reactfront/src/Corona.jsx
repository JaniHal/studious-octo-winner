import React, {useState, useEffect} from "react";
import ReactTable from "react-table-v6";
import "react-table-v6/react-table.css";



const Corona = props => {
	const data= [{
		country:'Finland',
		newCases:3,
		totalCases:5,
		newDeaths:2,
		totalDeaths:3,
		newRecoveries:8,
		totalRecoveries:20,
		date:'20-4-2020'
	}];

	const columns = [{
		Header:'Country',
		accessor:'country'
	},{
		Header:'New Cases',
		accessor:'newCases'
	},{
		Header:'Total Cases',
		accessor:'totalCases'
	},{
		Header:'New Deaths',
		accessor:'newDeaths'
	},{
		Header:'Total Deaths',
		accessor:'totalDeaths'
	},{
		Header:'New Recoveries',
		accessor:'newRecoveries'
	},{
		Header:'Total Recoveries',
		accessor:'totalRecoveries'
	},{
		Header:'Date',
		accessor:'date'
	}];

	return (
		<div>
		<h4>{props.title}</h4>
		<ReactTable data={data} colums={columns}/>
</div>);
};
	export default Corona;
