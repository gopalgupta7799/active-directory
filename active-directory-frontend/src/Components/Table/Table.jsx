import './Table.css'
import React from 'react'
import {Link} from "react-router-dom";

export default function Table({columns, rows, type}) {
  let {Id, ...columnsWithoutId} = columns;
  return (
    <table>
      <thead>
      <tr>
        {Object.keys(columns).map(key => <td key={key}>{key}</td>)}
      </tr>
      </thead>
      <tbody>
      {rows.map(row => (
        <tr key={row.id}>
          <td><Link to={`/${type}/${row.id}`} style={{color: "var(--link-color)"}}>{row.id}</Link></td>
          {Object.keys(columnsWithoutId).map(key => <td key={key}>{row[columns[key]]}</td>)}
        </tr>
      ))}
      </tbody>
    </table>
  )
}
