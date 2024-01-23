import './Pagination.css'
import React from 'react'

export default function Pagination({pages, pageNumber, setPageNumber}) {
  return (
    <div className={"pagination"}>
      {
        pages.map((page, i) =>
          <span key={i}
                className={"pageNumber"}
                style={pageNumber === page - 1 ? {"border": "2px solid var(--theme-color)"} : {}}
                onClick={(e) =>
                  setPageNumber(parseInt(e.currentTarget.innerText) - 1)
                }
          >{page}</span>)
      }
    </div>
  )
}
