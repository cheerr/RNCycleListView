/**
 * Created by chenwei on 2017/8/7.
 */

import React, { Component } from 'react'
import {
  requireNativeComponent,
} from 'react-native'
import RNListItemView from './RNListItemView'

const RNListView = requireNativeComponent('RNListView', null)

const CACHE_SIZE = 30

class Index extends Component {

  static getDefaultProps: {
    dataSource: [],
    onRowSize: undefined,  //(dataObject,index)=>{}
    renderRow: undefined,  //(dataObject,rowSizeObject)=>{}
  }

  constructor (props) {
    super(props)
    console.disableYellowBox = true
  }

  _renderRow (rowSizes) {

    let arr = []

    for (let i = 0; i < Math.min(rowSizes.length, CACHE_SIZE); i++) {
      arr.push(<RNListItemView
        key={i}
        rowHeight={rowSizes[i].rowHeight}
        renderRow={
          typeof this.props.renderRow === 'function' ? this.props.renderRow : null
        }
      />)
    }

    console.log('RNList  _renderRow length', arr.length)
    return arr
  }

  render () {

    let rowSizes = []

    for (let i = 0; i < this.props.dataSource.length; i++) {
      rowSizes.push(
        this.props.onRowSize(this.props.dataSource[i], i)
      )
    }

    console.log('RNList render rowSizes', rowSizes.length)

    return <RNListView
      {...this.props}
      rowSizes={rowSizes}
    >
      {
        this._renderRow(rowSizes)
      }
    </RNListView>

  }

}

export default Index