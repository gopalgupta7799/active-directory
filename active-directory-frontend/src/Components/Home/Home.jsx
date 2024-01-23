import "./Home.css"
import React from 'react'
import {Link} from "react-router-dom";

export default function Home() {
  return (
      <div id={"home"}>
        <Link className={"card"} to={"/users"}>Users</Link>
        <Link className={"card"} to={"/groups"}>Groups</Link>
      </div>
  )
}
